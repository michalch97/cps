package signalSerialization;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;
import signalGenerators.Point;
import signalGenerators.SignalGenerator;
import signalUtils.SignalParameters;
import signalUtils.SignalParameters.SignalParametersBuilder;
import signalUtils.SignalType;
import signals.FixedSignal;
import viewItems.SignalView;

@Slf4j
public class SignalSerializator {

    public void serializeSignal(String filePath, SignalView signalView) {
        SignalParameters signalParameters = signalView.getSignalParameters();
        SignalGenerator signalGenerator = new SignalGenerator(signalView.getSignal(), signalParameters.getStartTime(), signalParameters.getDuration(), 0.01d);

        List<Point> points = signalGenerator.generateSignal();
        Double timeStep = signalGenerator.getTimeStep();
        List<Double> yValues = points.stream().map(Point::getY).collect(Collectors.toList());

        SignalFileContent signalFileContent = new SignalFileContent(signalView.getName(), signalView.getSignalParameters(), timeStep, yValues.size(), yValues);
        saveToFile(filePath, signalFileContent);
    }

    public SignalView deserializeSignal(String filePath) {
        SignalFileContent signalFileContent = readFromFile(filePath);
        if (signalFileContent == null) {
            return null;
        }

        SignalParameters signalParameters = signalFileContent.getSignalParameters();
        Double timeStep = signalFileContent.getTimeStep();
        Integer sampleSize = signalFileContent.getSampleSize();
        List<Double> yValues = signalFileContent.getYValues();

        List<Double> xValues = DoubleStream.iterate(signalParameters.getStartTime(), value -> value + timeStep)
                                           .limit(sampleSize)
                                           .boxed()
                                           .collect(Collectors.toList());
        List<Point> points = IntStream.range(0, sampleSize)
                                      .mapToObj(it -> new Point(xValues.get(it), yValues.get(it)))
                                      .collect(Collectors.toList());

        FixedSignal fixedSignal = new FixedSignal(points, signalParameters.getAmplitude(), timeStep);
        return new SignalView(signalFileContent.getName(), fixedSignal, signalParameters, SignalType.FIXED_SIGNAL);
    }

    public String deserializeToString(String filePath) {
        SignalFileContent signalFileContent = readFromFile(filePath);
        if (signalFileContent == null) {
            return "";
        }

        return signalFileContent.toString();
    }

    private void saveToFile(String filePath, SignalFileContent signalFileContent) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath);
             DataOutputStream outputStream = new DataOutputStream(fileOutputStream)) {

            outputStream.writeUTF(signalFileContent.getName());
            SignalParameters signalParameters = signalFileContent.getSignalParameters();

            outputStream.writeDouble(signalParameters.getAmplitude());
            outputStream.writeDouble(signalParameters.getStartTime());
            outputStream.writeDouble(signalParameters.getDuration());
            outputStream.writeDouble(signalParameters.getPeriod());
            outputStream.writeDouble(signalParameters.getFillFactor());

            outputStream.writeDouble(signalFileContent.getTimeStep());
            outputStream.writeInt(signalFileContent.getSampleSize());
            for (Double value : signalFileContent.getYValues()) {
                outputStream.writeDouble(value);
            }
        } catch (IOException e) {
            log.error("Saving to file failed!", e);
        }
    }

    private SignalFileContent readFromFile(String filePath) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             DataInputStream inputStream = new DataInputStream(fileInputStream)) {

            String name = inputStream.readUTF();

            SignalParametersBuilder parametersBuilder = SignalParameters.builder();
            parametersBuilder.amplitude(inputStream.readDouble());
            parametersBuilder.startTime(inputStream.readDouble());
            parametersBuilder.duration(inputStream.readDouble());
            parametersBuilder.period(inputStream.readDouble());
            parametersBuilder.fillFactor(inputStream.readDouble());
            SignalParameters signalParameters = parametersBuilder.build();

            double timeStep = inputStream.readDouble();
            int sampleSize = inputStream.readInt();
            List<Double> yValues = new ArrayList<>(sampleSize);
            for (int i = 0; i < sampleSize; i++) {
                yValues.add(inputStream.readDouble());
            }

            return new SignalFileContent(name, signalParameters, timeStep, sampleSize, yValues);
        } catch (IOException e) {
            log.error("Reading from file failed!", e);
        }

        return null;
    }
}

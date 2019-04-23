package signalUtils;

import java.util.List;
import java.util.stream.Collectors;

import signalGenerators.Point;
import signalGenerators.SignalGenerator;
import signals.Signal;

public class SignalQuantization {
    public static List<Point> quantizeSignal(Signal signal, SignalParameters parameters, Double timeStep, Integer quantumClassCount) {
        List<Point> points = generateDiscretePoint(signal, parameters, timeStep);
        Double minAmplitude = signal.getMinAmplitude();
        Double maxAmplitude = signal.getMaxAmplitude();
        double offset = (maxAmplitude - minAmplitude) / 2;

        Double quantumClassLength = getQuantumClassLength(minAmplitude, maxAmplitude, quantumClassCount);

        return points.stream().map(point -> {
            int quantumClass = (int)((point.getY() - minAmplitude) / quantumClassLength);
            return new Point(point.getX(), quantumClass * quantumClassLength - offset);
        }).collect(Collectors.toList());
    }

    private static Double getQuantumClassLength(Double minAmplitude, Double maxAmplitude, Integer quantumClassCount) {
        Double amplitudeLength = maxAmplitude - minAmplitude;
        return amplitudeLength / quantumClassCount;
    }

    private static List<Point> generateDiscretePoint(Signal signal, SignalParameters parameters, Double timeStep) {
        Double startTime = parameters.getStartTime();
        SignalGenerator signalGenerator = new SignalGenerator(signal, startTime, startTime + parameters.getDuration(), timeStep);

        return signalGenerator.generateSignal();
    }
}

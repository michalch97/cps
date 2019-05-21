package radarSimulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import signalGenerators.Point;
import signalGenerators.SignalCorrelation;

public class ProximitySensor {

    private SimulatorSettingsDto settingsDto;

    public ProximitySensor(SimulatorSettingsDto settingsDto) {
        this.settingsDto = settingsDto;
    }

    public Double measureDistance(List<Point> sourceSignal, List<Point> reflectedSignal) {
        List<Point> correlation = getCorrelation(sourceSignal, reflectedSignal);
        Point maxCorrelationPoint = getCorrelationMax(correlation);

        return getDistance(correlation, maxCorrelationPoint);
    }

    private Double getDistance(List<Point> correlation, Point maxCorrelationPoint) {
        long discreetDistance = Math.round(maxCorrelationPoint.getX() - correlation.size() / 2.d);
        double timeToMaxPoint = (double) discreetDistance / settingsDto.getSignalSamplesPerSecond();

        return timeToMaxPoint * settingsDto.getWaveEnvironmentalSpeed() / 2.d;
    }

    private Point getCorrelationMax(List<Point> correlation) {
        int middlePointIndex = correlation.size() / 2;
        return correlation.stream()
                          .skip(middlePointIndex)
                          .max(Comparator.comparing(Point::getY))
                          .orElse(new Point(0.d, 0.d));
    }

    private List<Point> getCorrelation(List<Point> sourceSignal, List<Point> reflectedSignal) {
        SignalCorrelation signalCorrelation = new SignalCorrelation();
//        return signalCorrelation.correlation(sourceSignal, reflectedSignal, 1.d / settingsDto.getSignalSamplesPerSecond());
        return calculateCorrelation(sourceSignal, reflectedSignal);
    }

    private List<Point> calculateCorrelation(List<Point> sourceSignal, List<Point> reflectedSignal) {
        List<Point> rotatedSignal = new ArrayList<>(sourceSignal);
        Collections.reverse(rotatedSignal);

        List<Double> sums = IntStream.range(0, sourceSignal.size()).mapToDouble(currentRotation -> {
            double signalsMultiplicationSum = getSignalsMultiplicationSum(reflectedSignal, rotatedSignal);
            Collections.rotate(rotatedSignal, 1);
            return signalsMultiplicationSum;
        }).boxed().collect(Collectors.toList());

        return IntStream.range(0, sums.size())
                        .mapToObj(index -> new Point((double) index, sums.get(index)))
                        .collect(Collectors.toList());
    }

    private double getSignalsMultiplicationSum(List<Point> reflectedSignal, List<Point> rotatedSignal) {
        return IntStream.range(0, rotatedSignal.size())
                        .mapToDouble(index -> rotatedSignal.get(index).getY() * reflectedSignal.get(index).getY())
                        .sum();
    }
}

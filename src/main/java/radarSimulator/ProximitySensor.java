package radarSimulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import signalGenerators.Point;

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
        return calculateCorrelation(sourceSignal, reflectedSignal);
    }

    public List<Point> calculateCorrelation(List<Point> sourceSignal, List<Point> reflectedSignal) {
        List<Point> rotatedSignal = new ArrayList<>(sourceSignal);

        List<Double> leftSums = IntStream.range(1, sourceSignal.size()).mapToDouble(currentRotation -> {
            Collections.rotate(rotatedSignal, 1);
            return getSignalsPartialMultiplicationSum(reflectedSignal, rotatedSignal, 0,  currentRotation);
        }).boxed().collect(Collectors.toList());

        List<Point> rotatedSignal2 = new ArrayList<>(sourceSignal);
        List<Double> rightSums = IntStream.range(0, sourceSignal.size()).mapToDouble(currentRotation -> {
            double signalsMultiplicationSum = getSignalsPartialMultiplicationSum(reflectedSignal, rotatedSignal2, currentRotation, rotatedSignal2.size());
            Collections.rotate(rotatedSignal2, 1);
            return signalsMultiplicationSum;
        }).boxed().collect(Collectors.toList());

        ArrayList<Double> sums = new ArrayList<>(leftSums);
        sums.addAll(rightSums);

        return IntStream.range(0, sums.size())
                        .mapToObj(index -> new Point((double) index, sums.get(index)))
                        .collect(Collectors.toList());
    }

    private double getSignalsPartialMultiplicationSum(List<Point> reflectedSignal, List<Point> rotatedSignal, int start, int end) {
        return IntStream.range(start, end)
                        .mapToDouble(index -> rotatedSignal.get(index).getY() * reflectedSignal.get(index).getY())
                        .sum();
    }
}

package signalGenerators;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SignalConvolution {
    public List<Point> convolution(List<Point> firstPoints, List<Point> secondPoints, Double timeStep) {
        final int M = firstPoints.size();
        final int N = secondPoints.size();
        List<Point> firstSignal = new ArrayList<>(firstPoints);
        List<Point> secondSignal = new ArrayList<>(secondPoints);

        firstSignal.addAll(createFillerPoints(secondPoints, firstSignal));
        secondSignal.addAll(createFillerPoints(firstPoints, secondSignal));

        Double time = firstPoints.get(0).getX();
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < M + N - 1; i++) {
            if (i < firstSignal.size()) {
                points.add(new Point(time, add(firstSignal, secondSignal, 0, i)));
            } else {
                points.add(new Point(time, add(firstSignal, secondSignal, i - firstSignal.size() + 1, firstSignal.size() - 1)));
            }

            time += timeStep;
        }
        return points;
    }

    private List<Point> createFillerPoints(List<Point> longerSignal, List<Point> signalToBeFilled) {
        return longerSignal.stream()
                           .skip(signalToBeFilled.size())
                           .map(point -> new Point(point.getX(), 0.d))
                           .collect(Collectors.toList());
    }

    private double add(List<Point> firstPoints, List<Point> secondPoints, int start, int end) {
        Double sum = 0.d;
        for (int j = start, k = end; j <= end && k >= 0; j++, k--) {
            sum += firstPoints.get(j).getY() * secondPoints.get(k).getY();
        }
        return sum;
    }

}

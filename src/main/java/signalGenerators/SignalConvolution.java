package signalGenerators;

import java.util.ArrayList;
import java.util.List;

public class SignalConvolution {
    public List<Point> convolution(List<Point> firstPoints, List<Point> secondPoints, Double timeStep) {
        final int M = firstPoints.size();
        final int N = secondPoints.size();
        List<Point> firstSignal = new ArrayList<>();
        List<Point> secondSignal = new ArrayList<>();

        if (M < N) {
            for (int i = 0; i < M; i++) {
                firstSignal.add(firstPoints.get(i));
            }
            for (int i = M; i < N; i++) {
                firstSignal.add(new Point(secondPoints.get(i).getX(), 0.d));
            }
            secondSignal = secondPoints;
        } else if (M > N) {
            for (int i = 0; i < N; i++) {
                secondSignal.add(secondPoints.get(i));
            }
            for (int i = N; i < M; i++) {
                secondSignal.add(new Point(firstPoints.get(i).getX(), 0.d));
            }
            firstSignal = firstPoints;
        } else {
            firstSignal = firstPoints;
            secondSignal = secondPoints;
        }

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

    private double add(List<Point> firstPoints, List<Point> secondPoints, int start, int end) {
        Double sum = 0.d;
        for (int j = start, k = end; j <= end && k >= 0; j++, k--) {
            sum += firstPoints.get(j).getY() * secondPoints.get(k).getY();
        }
        return sum;
    }

}

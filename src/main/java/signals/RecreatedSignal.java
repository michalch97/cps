package signals;

import java.util.List;

import signalGenerators.Point;
import signalUtils.SignalStorageType;

public class RecreatedSignal extends Signal {
    private List<Point> points;
    private Double start;
    private Double timeStep;

    public RecreatedSignal(List<Point> points, Double start, Double timeStep, Double amplitude) {
        super(amplitude, SignalStorageType.Continuous);

        this.points = points;
        this.start = start;
        this.timeStep = timeStep;
    }

    @Override
    public Double calculateValue(Double xPoint) {
        int point = (int) ((xPoint - start) / timeStep);

        if (point >= 0 && point < points.size() - 1) {
            Point firstValue = points.get(point);
            Point secondValue = points.get(point + 1);

            double distanceToAnotherPoint = (secondValue.getX() - xPoint) / timeStep;
            return (secondValue.getY() - firstValue.getY()) * distanceToAnotherPoint;
        }

        return 0.d;
    }
}

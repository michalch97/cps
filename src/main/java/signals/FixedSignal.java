package signals;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import signalGenerators.Point;
import signalUtils.SignalStorageType;

@Getter
public class FixedSignal extends DiscreteSignal {

    private final List<Point> points;
    private Double timeStep;

    public FixedSignal(List<Point> points, Double amplitude, Double timeStep) {
        super(points.stream().map(Point::getX).collect(Collectors.toList()), SignalStorageType.Discrete, amplitude, timeStep);

        this.points = points;
        this.timeStep = timeStep;
    }

    @Override
    public Double calculateValue(Double xPoint) {
        Point point = points.stream().min(Comparator.comparingDouble(o -> Math.abs(o.getX() - xPoint))).get();
        return point.getY();
    }
}

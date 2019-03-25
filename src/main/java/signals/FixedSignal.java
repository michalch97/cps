package signals;

import java.util.Collection;
import java.util.stream.Collectors;

import exceptions.SignalOperationNotSupportedException;
import lombok.Getter;
import signalGenerators.Point;
import signalUtils.SignalStorageType;

@Getter
public class FixedSignal extends DiscreteSignal {

    private final Collection<Point> points;

    public FixedSignal(Collection<Point> points, Double amplitude, Double timeStep) {
        super(points.stream().map(Point::getX).collect(Collectors.toList()), SignalStorageType.Discrete, amplitude, timeStep);
        this.points = points;
    }

    @Override
    public Double calculateValue(Double xPoint) {
        throw new SignalOperationNotSupportedException();
    }
}

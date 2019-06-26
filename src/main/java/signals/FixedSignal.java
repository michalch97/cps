package signals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import signalGenerators.ComplexPoint;
import signalGenerators.Point;
import signalUtils.SignalStorageType;

@Getter
public class FixedSignal extends DiscreteSignal {

    private final List<Point> points;
    private final List<ComplexPoint> complexPoints;
    private Double timeStep;

    public FixedSignal(List<Point> points, Double amplitude, Double timeStep) {
        super(points.stream().map(Point::getX).collect(Collectors.toList()), SignalStorageType.Discrete, amplitude, timeStep);

        this.points = points;
        this.timeStep = timeStep;

        this.complexPoints = new ArrayList<>();
    }

    public FixedSignal(Double amplitude, List<ComplexPoint> points, Double timeStep) {
        super(points.stream().map(ComplexPoint::getX).collect(Collectors.toList()), SignalStorageType.Discrete, amplitude, timeStep);

        this.complexPoints = points;
        this.timeStep = timeStep;

        this.points = new ArrayList<>();
    }

    @Override
    public Double calculateValue(Double xPoint) {
        if(isFourier()){
             ComplexPoint point = complexPoints.stream().min(Comparator.comparingDouble(o->Math.abs(o.getX() - xPoint))).get();
             return point.getYReal();
        } else {
            Point point = points.stream().min(Comparator.comparingDouble(o -> Math.abs(o.getX() - xPoint))).get();
            return point.getY();
        }
    }

    public boolean isFourier(){
        return (points.size() == 0);
    }
}

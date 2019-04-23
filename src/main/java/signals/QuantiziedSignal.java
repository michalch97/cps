package signals;

import java.util.List;

import signalGenerators.Point;

public class QuantiziedSignal extends FixedSignal {
    public QuantiziedSignal(List<Point> points, Double amplitude, Double timeStep) {
        super(points, amplitude, timeStep);
    }
}

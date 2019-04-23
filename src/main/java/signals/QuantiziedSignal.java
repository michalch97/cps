package signals;

import java.util.Collection;

import exceptions.SignalOperationNotSupportedException;
import signalGenerators.Point;

public class QuantiziedSignal extends FixedSignal {

    public QuantiziedSignal(Collection<Point> points, Double amplitude, Double timeStep) {
        super(points, amplitude, timeStep);
    }

    @Override
    public Double calculateValue(Double xPoint) {
        throw new SignalOperationNotSupportedException();
    }
}

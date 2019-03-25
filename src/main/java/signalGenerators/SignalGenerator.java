package signalGenerators;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import lombok.Getter;
import lombok.NonNull;
import signalUtils.SignalStorageType;
import signals.DiscreteSignal;
import signals.Signal;

@Getter
@NonNull
public class SignalGenerator {
    private Signal signal;
    private Double startTime;
    private Double duration;
    private Double timeStep;

    public SignalGenerator(Signal signal, Double startTime, Double duration, Double timeStep){
        this.signal = signal;
        this.startTime = startTime;
        this.duration = duration;
        this.timeStep = timeStep;
    }

    public List<Point> generateSignal() {
        if (signal.getSignalStorageType() == SignalStorageType.Discrete) {
            return generateSignalForDiscretization();
        } else {
            List<Double> xValues = generateXValues();
            return calculateYValues(xValues);
        }
    }

    public List<Point> generateSignalForDiscretization() {
        List<Double> xValues;
        if (signal instanceof DiscreteSignal) {
            xValues = new ArrayList<>(((DiscreteSignal) signal).getXPoints());
        } else {
            xValues = generateDiscreteXValues();
        }

        return calculateYValues(xValues);
    }

    private List<Point> calculateYValues(List<Double> xValues) {
        List<Double> yValues = signal.calculateValues(xValues);

        return IntStream.range(0, xValues.size())
                        .mapToObj(it -> new Point(xValues.get(it), yValues.get(it)))
                        .collect(Collectors.toList());
    }

    private List<Double> generateDiscreteXValues() {
        return generateDiscreteXValues(startTime, duration, timeStep);
    }

    public static List<Double> generateDiscreteXValues(Double startTime, Double duration, Double timeStep) {
        int signalStartTime = (int)Math.ceil(startTime);
        int signalEndTime = (int)Math.floor(startTime + duration);
        return IntStream.range(signalStartTime, signalEndTime)
                        .limit((long) (duration / timeStep))
                        .mapToDouble(it -> it)
                        .boxed()
                        .collect(Collectors.toList());
    }

    private List<Double> generateXValues() {
        return generateXValues(startTime, duration, timeStep);
    }

    public static List<Double> generateXValues(Double startTime, Double duration, Double timeStep) {
        return DoubleStream.iterate(startTime, value -> value + timeStep)
                           .limit((long) (duration / timeStep))
                           .boxed()
                           .collect(Collectors.toList());
    }
}

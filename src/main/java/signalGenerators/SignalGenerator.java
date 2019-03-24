package signalGenerators;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import signals.DiscreteSignal;
import signals.Signal;

@Getter
@NonNull
public class SignalGenerator {
    private Signal signal;
    private DiscreteSignal discreteSignal;
    private Double startTime;
    private Double duration;
    private Double timeStep;

    public SignalGenerator(Signal signal, Double startTime, Double duration, Double timeStep){
        this.signal = signal;
        this.startTime = startTime;
        this.duration = duration;
        this.timeStep = timeStep;
    }

    public SignalGenerator(DiscreteSignal discreteSignal, Double startTime, Double duration, Double timeStep){
        this.discreteSignal = discreteSignal;
        this.startTime = startTime;
        this.duration = duration;
        this.timeStep = timeStep;
    }

    public List<Point> generateSignal() {
        List<Double> xValues = generateXValues();
        List<Double> yValues = signal.calculateValues(xValues);

        return IntStream.range(0, xValues.size())
                        .mapToObj(it -> new Point(xValues.get(it), yValues.get(it)))
                        .collect(Collectors.toList());
    }

    public List<Point> generateSignalForDiscretization() {
        List<Double> xValues = generateXValues();
        Map<Double,Double> values = discreteSignal.calculateValues(xValues);
//        List<Point> points = new ArrayList<>();
        Object[] array = values.keySet().toArray();
//        for(int i = 0;i<values.size();i++){
//            points.add(new Point((Double)array[i], values.get(array[i])));
//        }
//        return points;
        return IntStream.range(0, values.size())
                .mapToObj(it -> new Point((Double)array[it], values.get(array[it])))
                .collect(Collectors.toList());
    }

    private List<Double> generateXValues() {
        return DoubleStream.iterate(startTime, value -> value + timeStep)
                           .limit((long) (duration / timeStep))
                           .boxed()
                           .collect(Collectors.toList());
    }
}

package signalGenerators;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import signals.Signal;

@Getter
@NonNull
@AllArgsConstructor
public class SignalGenerator {
    private Signal signal;
    private Double startTime;
    private Double duration;
    private Double timeStep;

    public List<Point> generateSignal() {
        List<Double> xValues = generateXValues();
        List<Double> yValues = signal.calculateValues(xValues);

        return IntStream.range(0, xValues.size())
                        .mapToObj(it -> new Point(xValues.get(it), yValues.get(it)))
                        .collect(Collectors.toList());
    }

    private List<Double> generateXValues() {
        return DoubleStream.iterate(startTime, value -> value + timeStep)
                           .limit((long) (duration / timeStep))
                           .boxed()
                           .collect(Collectors.toList());
    }
}

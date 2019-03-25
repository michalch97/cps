package signals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.DoubleUnaryOperator;
import java.util.stream.Collectors;

import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import signalUtils.SignalStorageType;

@Getter
public abstract class Signal {
    private Double minAmplitude;
    private Double maxAmplitude;
    private SignalStorageType signalStorageType;

    public Signal(Double amplitude, SignalStorageType signalStorageType) {
        this.signalStorageType = signalStorageType;
        amplitude = amplitude < 0 ? -amplitude : amplitude;

        this.minAmplitude = -amplitude;
        this.maxAmplitude = amplitude;
    }

    abstract public Double calculateValue(Double xPoint);

    public List<Double> calculateValues(Collection<Double> xPoints) {
        if (CollectionUtils.isEmpty(xPoints)) {
            return new ArrayList<>();
        }
        return xPoints.stream().map(this::calculateValue).collect(Collectors.toList());
    }

    public Double averageSignal(Double start, Double end) {
        Double result = 1.d / (end - start);
        result *= signalIntegrator(start, end, this::calculateValue);
        return result;
    }

    public Double meanAverageSignal(Double start, Double end) {
        Double result = 1.d / (end - start);
        result *= signalIntegrator(start, end, x -> Math.abs(calculateValue(x)));
        return result;
    }

    public Double averageSignalPower(Double start, Double end) {
        Double result = 1.d / (end - start);
        result *= signalIntegrator(start, end, x -> calculateValue(x) * calculateValue(x));
        return result;
    }

    public Double variationOfSignal(Double start, Double end) {
        Double result = 1.d / (end - start);
        Double average = averageSignal(start, end);
        result *= signalIntegrator(start, end, x -> {
            double value = Math.abs(calculateValue(x) - average);
            return value * value;
        });
        return result;
    }

    public Double effectiveSignalValue(Double start, Double end) {
        Double result = averageSignalPower(start, end);
        return Math.sqrt(result);
    }

    public static Double signalIntegrator(Double start, Double end, DoubleUnaryOperator function) {
        Double stepSize = 0.001d;
        Double sum = 0.5d * (function.applyAsDouble(start) + function.applyAsDouble(end));

        Double steps = stepSize;
        while((start + steps) < end) {
            Double xValue = start + steps;
            sum += function.applyAsDouble(xValue);

            steps += stepSize;
        }

        return sum * stepSize;
    }
}

package signals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import signalGenerators.Point;
import signalUtils.SignalStorageType;

@Getter
public abstract class DiscreteSignal extends Signal {

    private Collection<Double> xPoints;
    private Double timeStep;

    public DiscreteSignal(Collection<Double> xPoints, SignalStorageType storageType, Double amplitude, Double timeStep) {
        super(amplitude, storageType);
        this.xPoints = xPoints;
        this.timeStep = timeStep;
    }

    public List<Double> calculateValues() {
        if (CollectionUtils.isEmpty(xPoints)) {
            return new ArrayList<>();
        }
        return xPoints.stream().map(this::calculateValue).collect(Collectors.toList());
    }

    @Override
    public Double averageSignal(Double start, Double end) {
        Point minAndMax = getMinAndMax();

        Double result = 1.d / (minAndMax.getY() - minAndMax.getX() + 1);
        result *= xPoints.stream().mapToDouble(this::calculateValue).sum();
        return result;
    }

    @Override
    public Double meanAverageSignal(Double start, Double end) {
        Point minAndMax = getMinAndMax();

        Double result = 1.d / (minAndMax.getY() - minAndMax.getX() + 1);
        result *= xPoints.stream().mapToDouble(this::calculateValue).map(Math::abs).sum();
        return result;
    }

    @Override
    public Double averageSignalPower(Double start, Double end) {
        Point minAndMax = getMinAndMax();

        Double result = 1.d / (minAndMax.getY() - minAndMax.getX() + 1);
        result *= xPoints.stream().mapToDouble(this::calculateValue).map(x -> x * x).sum();
        return result;
    }

    @Override
    public Double variationOfSignal(Double start, Double end) {
        Point minAndMax = getMinAndMax();
        Double average = averageSignal(start, end);

        Double result = 1.d / (minAndMax.getY() - minAndMax.getX() + 1);
        result *= xPoints.stream().mapToDouble(this::calculateValue).map(x -> x - average).map(Math::abs).sum();
        return result;
    }

    @Override
    public Double effectiveSignalValue(Double start, Double end) {
        Double result = averageSignalPower(start, end);
        return Math.sqrt(result);
    }

    private Point getMinAndMax() {
        Double start = xPoints.stream().min(Double::compareTo).get();
        Double end = xPoints.stream().max(Double::compareTo).get();

        return new Point(start, end);
    }
}

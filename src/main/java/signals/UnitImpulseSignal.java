package signals;

import java.util.Collection;
import java.util.Objects;

import signalUtils.SignalStorageType;

public class UnitImpulseSignal extends DiscreteSignal {

    private Double impulseSample;

    public UnitImpulseSignal(Collection<Double> xPoints, Double amplitude, Double impulseSample, Double timeStep) {
        super(xPoints, SignalStorageType.Discrete, amplitude, timeStep);
        this.impulseSample = impulseSample;
    }

    @Override
    public Double calculateValue(Double sample) {
        if (Objects.equals(sample, impulseSample)) {
            return getMaxAmplitude();
        }

        return 0.d;
    }
}

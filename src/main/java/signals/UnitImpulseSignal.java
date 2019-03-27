package signals;

import java.util.Collection;
import java.util.Objects;

import signalUtils.SignalStorageType;

public class UnitImpulseSignal extends DiscreteSignal {

    private Double impulseSample;
    private static Double jump;

    public UnitImpulseSignal(Collection<Double> xPoints, Double amplitude, Double impulseSample, Double timeStep) {
        super(xPoints, SignalStorageType.Discrete, amplitude, timeStep);
        this.impulseSample = impulseSample;
        this.jump = 0.d;
    }

    @Override
    public Double calculateValue(Double sample) {
        jump = jump + 1.d;
        if (Objects.equals(jump, impulseSample)) {
            return getMaxAmplitude();
        }

        return 0.d;
    }
}

package signals;

import java.util.Objects;

public class UnitImpulseSignal extends Signal {

    private Double impulseSample;

    public UnitImpulseSignal(Double amplitude, Double impulseSample) {
        super(amplitude);
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

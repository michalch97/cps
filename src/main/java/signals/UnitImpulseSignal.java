package signals;

import java.math.BigDecimal;
import java.util.*;

public class UnitImpulseSignal extends DiscreteSignal {

    private Integer firstSample;
    private Integer impulseSample;
    private Double frequency;
    private int precision;

    public UnitImpulseSignal(Double amplitude, Integer firstSample, Integer impulseSample, Double frequency) {
        super(amplitude);
        this.firstSample = firstSample;
        this.impulseSample = impulseSample;
        this.frequency = frequency;
        this.precision = BigDecimal.valueOf(frequency).scale();
    }

    @Override
    public Double calculateValue(Integer sample) {
        if (sample == impulseSample)
            return getMaxAmplitude();
        return 0.d;
    }

    @Override
    public Map<Double, Double> calculateValues(List<Double> xPoints) {

        List<Double> x = new ArrayList<>();
        Double start = xPoints.get(0);
        Double end = xPoints.get(xPoints.size() - 1);

        while (start < end) {
            x.add(start);
            start = start + frequency;

        }

        Map<Double, Double> map = new HashMap<>();

        for (int i = 0; i < x.size(); i++) {
            map.put(x.get(i), calculateValue(i));
        }
        return map;
    }
}

package signals;

import java.util.*;

public class ImpulseNoiseSignal extends DiscreteSignal {

    private Double frequency;
    private Double probability;
    private Random rand;

    public ImpulseNoiseSignal(Double amplitude, Double frequency, Double probability) {
        super(amplitude);
        this.frequency = frequency;
        this.probability = probability;
        this.rand = new Random();
    }

    @Override
    public Double calculateValue(Integer sample) {
        rand.setSeed(sample);
        double temp = rand.nextDouble();
        if (temp <= probability)
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

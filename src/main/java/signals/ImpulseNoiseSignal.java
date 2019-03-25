package signals;

import java.util.Random;

import signalUtils.SignalStorageType;

public class ImpulseNoiseSignal extends Signal {

    private Double frequency;
    private Double probability;
    private Random rand;

    public ImpulseNoiseSignal(Double amplitude, Double frequency, Double probability) {
        super(amplitude, SignalStorageType.Continuous);
        this.frequency = frequency;
        this.probability = probability;
        this.rand = new Random();
    }

    @Override
    public Double calculateValue(Double sample) {
        rand.setSeed(sample.longValue());
        double temp = rand.nextDouble();

        if (temp <= probability) {
            return getMaxAmplitude();
        }

        return 0.d;
    }
}

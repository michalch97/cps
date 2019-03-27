package signals;

import java.util.Collection;
import java.util.Random;

import signalUtils.SignalStorageType;


public class ImpulseNoiseSignal extends DiscreteSignal {

    private Double frequency;
    private Double probability;
    private Random rand;

    public ImpulseNoiseSignal(Collection<Double> xPoints, Double amplitude, Double probability, Double timeStep) {
        super(xPoints, SignalStorageType.Discrete, amplitude, timeStep);
        this.probability = probability;
        this.rand = new Random();
    }

    @Override
    public Double calculateValue(Double xPoint) {
        double temp = rand.nextDouble();
        if (temp <= probability)
            return getMaxAmplitude();
        return 0.d;
    }
}


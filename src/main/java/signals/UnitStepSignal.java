package signals;

import java.util.Collection;
import java.util.Objects;

import signalUtils.SignalStorageType;

public class UnitStepSignal extends DiscreteSignal {
    private Double startTime;

    public UnitStepSignal(Collection<Double> xPoints, Double amplitude, Double startTime){
        super(xPoints, SignalStorageType.Discrete, amplitude);
        this.startTime = startTime;
    }

    @Override
    public Double calculateValue(Double xPoint) {
        if(xPoint>startTime)
            return getMaxAmplitude();
        if(Objects.equals(xPoint, startTime))
            return getMaxAmplitude()/2.d;
        return 0.d;
    }
}

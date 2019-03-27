package signals;

import java.util.Collection;
import java.util.Objects;

import signalUtils.SignalStorageType;


public class UnitStepSignal extends Signal {
    private Double jumpTime;

    public UnitStepSignal(Double amplitude, Double jumpTime){
        super(amplitude, SignalStorageType.Continuous);
        this.jumpTime = jumpTime;
    }
    @Override
    public Double calculateValue(Double xPoint) {
        if(xPoint>jumpTime)
            return getMaxAmplitude();
        if(xPoint==jumpTime)
            return getMaxAmplitude()/2.d;
        return 0.d;
    }
}

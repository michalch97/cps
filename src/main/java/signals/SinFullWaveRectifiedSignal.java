package signals;

import signalUtils.SignalStorageType;

import static java.lang.Math.*;

public class SinFullWaveRectifiedSignal extends Signal {
    private Double period;
    private Double startTime;
    public SinFullWaveRectifiedSignal(Double amplitude, Double period, Double startTime){
        super(amplitude, SignalStorageType.Continuous);
        this.period = period;
        this.startTime = startTime;
    }
    @Override
    public Double calculateValue(Double xPoint) {
        return getMaxAmplitude()*abs(sin(2*PI*(xPoint - startTime)/period));
    }
}

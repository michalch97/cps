package signals;

import static java.lang.Math.*;

public class SinHalfWaveRectifiedSignal extends Signal {

    private Double period;
    private Double startTime;
    public SinHalfWaveRectifiedSignal(Double amplitude, Double period, Double startTime){
        super(amplitude);
        this.period = period;
        this.startTime = startTime;
    }
    @Override
    public Double calculateValue(Double xPoint) {
        return getMaxAmplitude()*(sin(2*PI*(xPoint - startTime)/period) + abs(sin(2*PI*(xPoint - startTime)/period)))/2.d;
    }
}

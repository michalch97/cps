package signals;

import static java.lang.Math.PI;
import static java.lang.Math.sin;

public class SinSignal extends Signal {

    private Double period;
    private Double startTime;
    public SinSignal(Double amplitude, Double period, Double startTime){
        super(amplitude);
        this.period = period;
        this.startTime = startTime;
    }

    @Override
    public Double calculateValue(Double xPoint) {
        return getMaxAmplitude()*sin(2*PI*(xPoint - startTime)/period);
    }
}

package signals;

import signalUtils.SignalStorageType;

public class RectangularSignal extends Signal {
    private Double period;
    private Double startTime;
    private Double dutyCycle;
    private Double k;

    public RectangularSignal(Double amplitude, Double period, Double startTime, Double dutyCycle) {
        super(amplitude, SignalStorageType.Continuous);
        this.period = period;
        this.startTime = startTime;
        this.dutyCycle = dutyCycle;//przez jaka czesc okresu wartosc ma byc rowna A
        k = 0.d;
    }

    @Override
    public Double calculateValue(Double xPoint) {
        if (xPoint > (k + 1.d) * period) {
            k = k + 1.d;
        }
        if ((xPoint >= k * period + startTime) && (xPoint < dutyCycle * period + k * period + startTime)) {
            return getMaxAmplitude();
        }
        else {
            return 0.d;
        }
    }
}

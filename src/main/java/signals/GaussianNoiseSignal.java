package signals;

import java.util.Random;

import signalUtils.SignalStorageType;

public class GaussianNoiseSignal extends Signal {
    private static final Long doubleToLongExtenderConst = 1_000_000_000L;
    Random rand;

    public GaussianNoiseSignal(Double amplitude) {
        super(amplitude, SignalStorageType.Continuous);
        rand = new Random();
    }

    @Override
    public Double calculateValue(Double xPoint) {
        rand.setSeed(pointToSeed(xPoint));
        return expandRandomValueToAmplitude(rand.nextGaussian()/3.5d);
    }

    private Double expandRandomValueToAmplitude(Double value) {
        return getMaxAmplitude()* value;
    }

    private long pointToSeed(Double point) {
        Double decimalExtendedPoint = point * doubleToLongExtenderConst;
        return decimalExtendedPoint.longValue();
    }
}
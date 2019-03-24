package signals;

import java.util.Random;

public class GaussianNoiseSignal extends Signal {
    private static final Long doubleToLongExtenderConst = 1_000_000_000L;
    Random rand;

    public GaussianNoiseSignal(Double amplitude) {
        super(amplitude);
        rand = new Random();
    }
    @Override
    public Double calculateValue(Double xPoint) {
        rand.setSeed(pointToSeed(xPoint));
        return expandRandomValueToAmplitude(rand.nextGaussian());
    }

    private Double expandRandomValueToAmplitude(Double value) {
        return getMinAmplitude() + (getMaxAmplitude() - getMinAmplitude()) * value;
    }

    private long pointToSeed(Double point) {
        Double decimalExtendedPoint = point * doubleToLongExtenderConst;
        return decimalExtendedPoint.longValue();
    }
}

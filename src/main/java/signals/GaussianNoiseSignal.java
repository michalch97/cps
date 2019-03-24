package signals;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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

    @Override
    public List<Double> calculateValues(Collection<Double> xPoints) {
        if (CollectionUtils.isEmpty(xPoints)) {
            return new ArrayList<>();
        }

        return xPoints.stream().map(this::calculateValue).collect(Collectors.toList());
    }

    private Double expandRandomValueToAmplitude(Double value) {
        return getMinAmplitude() + (getMaxAmplitude() - getMinAmplitude()) * value;
    }

    private long pointToSeed(Double point) {
        Double decimalExtendedPoint = point * doubleToLongExtenderConst;
        return decimalExtendedPoint.longValue();
    }
}

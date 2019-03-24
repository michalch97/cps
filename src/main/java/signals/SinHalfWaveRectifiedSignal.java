package signals;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Double> calculateValues(Collection<Double> xPoints) {
        if (CollectionUtils.isEmpty(xPoints)) {
            return new ArrayList<>();
        }
        return xPoints.stream().map(this::calculateValue).collect(Collectors.toList());
    }
}

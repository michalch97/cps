package signals;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UnitStepSignal extends Signal {
    private Double startTime;

    public UnitStepSignal(Double amplitude, Double startTime){
        super(amplitude);
        this.startTime = startTime;
    }
    @Override
    public Double calculateValue(Double xPoint) {
        if(xPoint>startTime)
            return getMaxAmplitude();
        if(xPoint==startTime)
            return getMaxAmplitude()/2.d;
        return 0.d;
    }

    @Override
    public List<Double> calculateValues(Collection<Double> xPoints) {
        if (CollectionUtils.isEmpty(xPoints)) {
            return new ArrayList<>();
        }
        return xPoints.stream().map(this::calculateValue).collect(Collectors.toList());
    }
}

package signals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;

@Getter
public abstract class Signal {
    private Double minAmplitude;
    private Double maxAmplitude;
    public Signal(Double amplitude) {
        amplitude = amplitude < 0 ? -amplitude : amplitude;

        this.minAmplitude = -amplitude;
        this.maxAmplitude = amplitude;
    }

    abstract public Double calculateValue(Double xPoint);

    public List<Double> calculateValues(Collection<Double> xPoints) {
        if (CollectionUtils.isEmpty(xPoints)) {
            return new ArrayList<>();
        }
        return xPoints.stream().map(this::calculateValue).collect(Collectors.toList());
    }
}

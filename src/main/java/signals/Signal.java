package signals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import signalUtils.SignalStorageType;

@Getter
public abstract class Signal {
    private Double minAmplitude;
    private Double maxAmplitude;
    private SignalStorageType signalStorageType;

    public Signal(Double amplitude, SignalStorageType signalStorageType) {
        this.signalStorageType = signalStorageType;
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

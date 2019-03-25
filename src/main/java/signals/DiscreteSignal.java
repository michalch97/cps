package signals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import signalUtils.SignalStorageType;

@Getter
public abstract class DiscreteSignal extends Signal {

    private Collection<Double> xPoints;

    public DiscreteSignal(Collection<Double> xPoints, SignalStorageType storageType, Double amplitude) {
        super(amplitude, storageType);
        this.xPoints = xPoints;
    }

    public List<Double> calculateValues() {
        if (CollectionUtils.isEmpty(xPoints)) {
            return new ArrayList<>();
        }
        return xPoints.stream().map(this::calculateValue).collect(Collectors.toList());
    }
}

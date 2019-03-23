package signals;

import java.util.Collection;
import java.util.List;

import lombok.Getter;

@Getter
public abstract class Signal {
    private Double minAmplitude;
    private Double maxAmplitude;

    public Signal(Double amplitude) {
        amplitude = amplitude < 0 ? -amplitude : amplitude;

        this.minAmplitude = -amplitude;
        this.maxAmplitude = amplitude;
    }

    abstract Double calculateValue(Double xPoint);
    abstract List<Double> calculateValues(Collection<Double> xPoints);
}

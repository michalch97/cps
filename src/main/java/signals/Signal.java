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

    abstract public Double calculateValue(Double xPoint);
    abstract public List<Double> calculateValues(Collection<Double> xPoints);
}

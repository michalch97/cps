package signals;

import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public abstract class DiscreteSignal {
    private Double minAmplitude;
    private Double maxAmplitude;

    public DiscreteSignal(Double amplitude) {
        amplitude = amplitude < 0 ? -amplitude : amplitude;

        this.minAmplitude = -amplitude;
        this.maxAmplitude = amplitude;
    }

    abstract public Double calculateValue(Integer sample);

    abstract public Map<Double, Double> calculateValues(List<Double> xPoints);
}

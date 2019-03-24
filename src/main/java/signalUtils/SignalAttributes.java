package signalUtils;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SignalAttributes {
    private Double average;
    private Double meanAverage;
    private Double effectiveValue;
    private Double variation;
    private Double averagePower;

    private SignalParameters signalParameters;
}

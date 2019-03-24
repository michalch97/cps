package signalUtils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignalParameters {
    private Double amplitude;
    private Double startTime;
    private Double duration;
    private Double period;
    private Double FillFactor;
}

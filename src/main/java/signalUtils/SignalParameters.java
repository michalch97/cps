package signalUtils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class SignalParameters {
    private Double amplitude;
    private Double startTime;
    private Double jumpTime;
    private Double duration;
    private Double period;
    private Double fillFactor;
}

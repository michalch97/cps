package viewItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import signalUtils.SignalParameters;
import signalUtils.SignalType;
import signals.Signal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignalView {
    private String name;
    private Signal signal;
    private SignalParameters signalParameters;
    private SignalType signalType;
}

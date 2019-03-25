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
    String name;
    Signal signal;
    SignalParameters signalParameters;
    SignalType signalType;
}

package signalGenerators;

import lombok.Getter;
import lombok.NonNull;
import signals.FixedSignal;
import signals.Signal;

import java.util.ArrayList;
import java.util.List;

@Getter
@NonNull
public class SignalGeneratorFourier {
    private Signal signal;

    public SignalGeneratorFourier(Signal signal){
        this.signal = signal;
    }

    public List<ComplexPoint> generateSignal() {
        return new ArrayList<>(((FixedSignal) signal).getComplexPoints());
    }
}

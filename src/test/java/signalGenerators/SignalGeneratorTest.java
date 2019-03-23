package signalGenerators;

import java.util.List;

import org.junit.jupiter.api.Test;
import signals.NoiseSignal;

import static org.junit.jupiter.api.Assertions.*;

class SignalGeneratorTest {

    @Test
    void generateSignal() {
        NoiseSignal noiseSignal = new NoiseSignal(10.d);
        SignalGenerator signalGenerator = new SignalGenerator(noiseSignal, 0.d, 5.d, 0.1d);

        List<Point> points = signalGenerator.generateSignal();

        assertEquals(50, points.size());
    }
}
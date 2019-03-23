package signals;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

class SignalTest {

    private static Double amplitude = 5.d;

    @ParameterizedTest
    @MethodSource("getSignalImplementations")
    void calculateValueShouldBeRepeatable(Signal signal) {
        // given
        Double xPoint = 5.d;

        // when
        Double firstExecution = signal.calculateValue(xPoint);
        Double secondExecution = signal.calculateValue(xPoint);

        // then
        assertEquals(firstExecution, secondExecution);
    }

    @ParameterizedTest
    @MethodSource("getSignalImplementations")
    void calculatedMultipleValuesShouldBeRepeatable(Signal signal) {
        // given
        List<Double> xPoint = new Random().doubles(0.d, 20.d).limit(10).boxed().collect(toList());

        // when
        List<Double> firstExecution = signal.calculateValues(xPoint);
        List<Double> secondExecution = signal.calculateValues(xPoint);

        // then
        assertNotNull(firstExecution);
        assertNotNull(secondExecution);
        assertTrue(CollectionUtils.isEqualCollection(firstExecution, secondExecution));
    }

    @ParameterizedTest
    @MethodSource("getSignalImplementations")
    void calculatedValuesShouldBeInsideAmplitude(Signal signal) {
        // given
        List<Double> xPoint = new Random().doubles(0.d, 20.d).limit(100).boxed().collect(toList());

        // when
        List<Double> results = signal.calculateValues(xPoint);

        // then
        assertNotNull(results);
        assertFalse(results.stream().anyMatch(value -> value > amplitude ||
                                                       value < -amplitude));
    }

    private static Stream<Signal> getSignalImplementations() {
        return Stream.of(new NoiseSignal(amplitude));
    }
}
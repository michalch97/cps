package signals;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import signalGenerators.Point;
import signalGenerators.SignalGenerator;
import signalGenerators.SignalOperations;

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

    @Test
    void addTwoSignals(){
        SinSignal sinSignal1 = new SinSignal(10.d, 6.d, 0.d);
        SinSignal sinSignal2 = new SinSignal(10.d, 6.d, 0.d);
        SignalGenerator signalGenerator1 = new SignalGenerator(sinSignal1, 0.d, 5.d, 0.1d);
        SignalGenerator signalGenerator2 = new SignalGenerator(sinSignal2, 0.d, 5.d, 0.1d);
        List<Point> points1 = signalGenerator1.generateSignal();
        List<Point> points2 = signalGenerator2.generateSignal();
        SignalOperations signalOperations = new SignalOperations();
        List<Point> addedSignals = signalOperations.add(points1,points2,0.1d);
        assertEquals(50, addedSignals.size());
    }

    @Test
    void substractTwoSignals(){
        SinSignal sinSignal1 = new SinSignal(10.d, 6.d, 0.d);
        SinSignal sinSignal2 = new SinSignal(10.d, 6.d, 0.d);
        SignalGenerator signalGenerator1 = new SignalGenerator(sinSignal1, 0.d, 5.d, 0.1d);
        SignalGenerator signalGenerator2 = new SignalGenerator(sinSignal2, 0.d, 5.d, 0.1d);
        List<Point> points1 = signalGenerator1.generateSignal();
        List<Point> points2 = signalGenerator2.generateSignal();
        SignalOperations signalOperations = new SignalOperations();
        List<Point> signalAfterSubstraction = signalOperations.subtract(points1,points2,0.1d);
        assertEquals(50, signalAfterSubstraction.size());
    }

    @Test
    void multiplyTwoSignals(){
        SinSignal sinSignal1 = new SinSignal(10.d, 6.d, 0.d);
        SinSignal sinSignal2 = new SinSignal(10.d, 6.d, 0.d);
        SignalGenerator signalGenerator1 = new SignalGenerator(sinSignal1, 0.d, 5.d, 0.1d);
        SignalGenerator signalGenerator2 = new SignalGenerator(sinSignal2, 0.d, 5.d, 0.1d);
        List<Point> points1 = signalGenerator1.generateSignal();
        List<Point> points2 = signalGenerator2.generateSignal();
        SignalOperations signalOperations = new SignalOperations();
        List<Point> signalAfterMultiplication = signalOperations.multiply(points1,points2,0.1d);
        assertEquals(50, signalAfterMultiplication.size());
    }

    @Test
    void divideTwoSignals(){
        SinSignal sinSignal1 = new SinSignal(10.d, 6.d, 0.d);
        SinSignal sinSignal2 = new SinSignal(10.d, 6.d, 2d);
        SignalGenerator signalGenerator1 = new SignalGenerator(sinSignal1, 0.d, 5.d, 0.1d);
        SignalGenerator signalGenerator2 = new SignalGenerator(sinSignal2, 1.5d, 5.d, 0.1d);
        List<Point> points1 = signalGenerator1.generateSignal();
        List<Point> points2 = signalGenerator2.generateSignal();
        SignalOperations signalOperations = new SignalOperations();
        List<Point> signalAfterDivision = signalOperations.divide(points1,points2,0.1d);
        assertEquals(50, signalAfterDivision.size());
    }

    private static Stream<Signal> getSignalImplementations() {
        return Stream.of(new NoiseSignal(amplitude));
    }

    @Test
    void signalIntegrator() {
        Double result = Signal.signalIntegrator(1.d, 5.d, Math::exp);

        assertEquals(145.59, result, 0.3d);
    }
}
package signalGenerators;

import java.util.List;

import org.junit.jupiter.api.Test;
import signals.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SignalGeneratorTest {

    @Test
    void generateNoiseSignal() {
        NoiseSignal noiseSignal = new NoiseSignal(10.d);
        SignalGenerator signalGenerator = new SignalGenerator(noiseSignal, 0.d, 5.d, 0.1d);

        List<Point> points = signalGenerator.generateSignal();

        assertEquals(50, points.size());
    }

    @Test
    void generateGaussianNoiseSignal() {
        GaussianNoiseSignal gaussianNoiseSignal = new GaussianNoiseSignal(10.d);
        SignalGenerator signalGenerator = new SignalGenerator(gaussianNoiseSignal, 0.d, 5.d, 0.1d);

        List<Point> points = signalGenerator.generateSignal();

        assertEquals(50, points.size());
    }

    @Test
    void generateSinSignal() {
        SinSignal sinSignal = new SinSignal(10.d, 6.d, 0.d);
        SignalGenerator signalGenerator = new SignalGenerator(sinSignal, 0.d, 5.d, 0.1d);

        List<Point> points = signalGenerator.generateSignal();

        assertEquals(50, points.size());
    }

    @Test
    void generateSinHalfWaveRectifiedSignal() {
        SinHalfWaveRectifiedSignal sinHalfWaveRectifiedSignal = new SinHalfWaveRectifiedSignal(10.d, 6.d, 0.d);
        SignalGenerator signalGenerator = new SignalGenerator(sinHalfWaveRectifiedSignal, 0.d, 5.d, 0.1d);

        List<Point> points = signalGenerator.generateSignal();

        assertEquals(50, points.size());
    }

    @Test
    void generateSinFullWaveRectifiedSignal() {
        SinFullWaveRectifiedSignal sinFullWaveRectifiedSignal = new SinFullWaveRectifiedSignal(10.d, 6.d, 0.d);
        SignalGenerator signalGenerator = new SignalGenerator(sinFullWaveRectifiedSignal, 0.d, 5.d, 0.1d);
        List<Point> points = signalGenerator.generateSignal();

        assertEquals(50, points.size());
    }

    @Test
    void generateRectangularSignal() {
        RectangularSignal rectangularSignal = new RectangularSignal(10.d, 2.d, 0.d, 0.5);
        SignalGenerator signalGenerator = new SignalGenerator(rectangularSignal, 0.d, 5.d, 0.1d);

        List<Point> points = signalGenerator.generateSignal();

        assertEquals(50, points.size());
    }

    @Test
    void generateRectangularSymmetricalSignal() {
        RectangularSymmetricalSignal rectangularSymmetricalSignal = new RectangularSymmetricalSignal(10.d, 2.d, 0.d, 0.5);
        SignalGenerator signalGenerator = new SignalGenerator(rectangularSymmetricalSignal, 0.d, 5.d, 0.1d);

        List<Point> points = signalGenerator.generateSignal();

        assertEquals(50, points.size());
    }

    @Test
    void generateTriangularSymmetricalSignal() {
        TriangularSignal triangularSignal = new TriangularSignal(10.d, 2.d, 0.d, 0.5);
        SignalGenerator signalGenerator = new SignalGenerator(triangularSignal, 0.d, 5.d, 0.1d);

        List<Point> points = signalGenerator.generateSignal();

        assertEquals(50, points.size());
    }

    @Test
    void generateUnitStepSignal() {
        Double timeStep = 0.1d;
        List<Double> xValues = SignalGenerator.generateDiscreteXValues(-10.d, 20.d, timeStep);
        UnitStepSignal unitStepSignal = new UnitStepSignal(xValues, 10.d, 0.d, timeStep);
        SignalGenerator signalGenerator = new SignalGenerator(unitStepSignal, -10.d, 20.d, 0.1d);

        List<Point> points = signalGenerator.generateSignal();

        assertEquals(20, points.size());
    }

    @Test
    void generateUnitImpulseSignal() {
        Double timeStep = 0.0001d;
        List<Double> xValues = SignalGenerator.generateDiscreteXValues(-25.d, 50.d, timeStep);

        UnitImpulseSignal unitImpulseSignal = new UnitImpulseSignal(xValues, 10.d, 1.d, timeStep);
        SignalGenerator signalGenerator = new SignalGenerator(unitImpulseSignal, -25.d, 50.d, 0.0001d);

        List<Point> points = signalGenerator.generateSignalForDiscretization();

        assertEquals(50, points.size());
    }

    @Test
    void generateImpulseNoiseSignal() {
        ImpulseNoiseSignal impulseNoiseSignal = new ImpulseNoiseSignal(10.d, 0.001d, 0.75);
        SignalGenerator signalGenerator = new SignalGenerator(impulseNoiseSignal, -25.d, 50.d, 0.0001d);

        List<Point> points = signalGenerator.generateSignalForDiscretization();

        assertEquals(50, points.size());
    }
}
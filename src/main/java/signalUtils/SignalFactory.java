package signalUtils;

import java.util.List;

import exceptions.UnknownSignalTypeException;
import signalGenerators.SignalGenerator;
import signals.*;

public class SignalFactory {

    public static Signal createSignal(SignalParameters parameters, SignalType type) {
        List<Double> xValues = SignalGenerator.generateDiscreteXValues(parameters.getStartTime(), parameters.getDuration(), 1.d);

        switch (type) {
            case GAUSSIAN_NOISE:
                return new GaussianNoiseSignal(parameters.getAmplitude());
            case IMPULSE_NOISE:
                return new ImpulseNoiseSignal(parameters.getAmplitude(), parameters.getPeriod(), parameters.getFillFactor());
            case NOISE:
                return new NoiseSignal(parameters.getAmplitude());
            case RECTANGULAR:
                return new RectangularSignal(parameters.getAmplitude(), parameters.getPeriod(), parameters.getStartTime(), parameters.getFillFactor());
            case RECTANGULAR_SYMMETRICAL:
                return new RectangularSymmetricalSignal(parameters.getAmplitude(), parameters.getPeriod(), parameters.getStartTime(), parameters.getFillFactor());
            case SIN_FULL_WAVE:
                return new SinFullWaveRectifiedSignal(parameters.getAmplitude(), parameters.getPeriod(), parameters.getStartTime());
            case SIN_HALF_WAVE:
                return new SinHalfWaveRectifiedSignal(parameters.getAmplitude(), parameters.getPeriod(), parameters.getStartTime());
            case SIN:
                return new SinSignal(parameters.getAmplitude(), parameters.getPeriod(), parameters.getStartTime());
            case TRIANGULAR:
                return new TriangularSignal(parameters.getAmplitude(), parameters.getPeriod(), parameters.getStartTime(), parameters.getFillFactor());
            case UNIT_IMPULSE:
                return new UnitImpulseSignal(xValues, parameters.getAmplitude(), parameters.getStartTime());
            case UNIT_STEP:
                return new UnitStepSignal(xValues, parameters.getAmplitude(), parameters.getStartTime());
            default:
                throw new UnknownSignalTypeException("Error during signal creation");
        }
    }
}

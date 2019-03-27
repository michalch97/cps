package signalUtils;

import java.util.List;

import exceptions.UnknownSignalTypeException;
import signalGenerators.SignalGenerator;
import signals.*;

public class SignalFactory {

    public static Signal createSignal(SignalParameters parameters, SignalType type) {
        Double discreteTimeStep = 1.d;
        List<Double> xValues = SignalGenerator.generateDiscreteXValues(parameters.getStartTime(), parameters.getDuration(), 1.d);

        switch (type) {
            case GAUSSIAN_NOISE://szum gaussowski (S2)
                return new GaussianNoiseSignal(parameters.getAmplitude());
            case IMPULSE_NOISE://szum impulsowy (S11)
                return new ImpulseNoiseSignal(xValues, parameters.getAmplitude(), parameters.getFillFactor(), discreteTimeStep);
            case NOISE://szum o rozkładzie jednostajnym (S1)
                return new NoiseSignal(parameters.getAmplitude());
            case RECTANGULAR://sygnał prostokątny(S6)
                return new RectangularSignal(parameters.getAmplitude(), parameters.getPeriod(), parameters.getStartTime(), parameters.getFillFactor());
            case RECTANGULAR_SYMMETRICAL:// sygnał prostokątny symetryczny(S7)
                return new RectangularSymmetricalSignal(parameters.getAmplitude(), parameters.getPeriod(), parameters.getStartTime(), parameters.getFillFactor());
            case SIN_FULL_WAVE:// sygnał sinusoidalny wyprostowany dwupołówkowo (S5)
                return new SinFullWaveRectifiedSignal(parameters.getAmplitude(), parameters.getPeriod(), parameters.getStartTime());
            case SIN_HALF_WAVE:// sygnał sinusoidalny wyprostowany jednopołówkowo (S4)
                return new SinHalfWaveRectifiedSignal(parameters.getAmplitude(), parameters.getPeriod(), parameters.getStartTime());
            case SIN://sygnał sinusoidalny (S3)
                return new SinSignal(parameters.getAmplitude(), parameters.getPeriod(), parameters.getStartTime());
            case TRIANGULAR:// sygnał trójkątny (S8)
                return new TriangularSignal(parameters.getAmplitude(), parameters.getPeriod(), parameters.getStartTime(), parameters.getFillFactor());
            case UNIT_IMPULSE:// impuls jednostkowy (S10)
                return new UnitImpulseSignal(xValues, parameters.getAmplitude(), parameters.getJumpTime(), discreteTimeStep);
            case UNIT_STEP:// skok jednostkowy (S9)
                return new UnitStepSignal(parameters.getAmplitude(),parameters.getJumpTime());
            default:
                throw new UnknownSignalTypeException("Error during signal creation");
        }
    }
}

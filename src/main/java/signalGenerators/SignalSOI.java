package signalGenerators;

import signalUtils.WindowType;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class SignalSOI {
    public List<Point> filter(List<Point> points, Double MValue, Double KValue, Double timeStep, WindowType windowType) {

        List<Double> lowPassFilterCoefficients = generateLowPassFilterCoefficients(MValue, KValue);
        List<Double> coefficients;
        if (windowType == WindowType.HANNING) {
            coefficients = generateHanningWindowsCoefficients(lowPassFilterCoefficients, MValue);
        } else {
            coefficients = lowPassFilterCoefficients;
        }

        List<Point> impulseResponse = new ArrayList<>();
        for (int i = 0; i < MValue.intValue(); i++) {
            impulseResponse.add(new Point(points.get(i).getX(), coefficients.get(i)));
        }

        SignalConvolution signalConvolution = new SignalConvolution();
        List<Point> signalAfterFiltration = signalConvolution.convolution(impulseResponse, points, timeStep);

        return signalAfterFiltration;
    }

    private List<Double> generateLowPassFilterCoefficients(Double M, Double K) {
        List<Double> coefficients = new ArrayList<>();
        for (int n = 0; n < M.intValue(); n++) {
            if (n == (M.intValue() - 1) / 2) {
                coefficients.add(2.d / K);
            } else {
                coefficients.add(sin(2.d * PI * (n - (M - 1.d) / 2.d) / K) / (PI * (n - (M - 1.d) / 2.d)));
            }
        }
        return coefficients;
    }

    private List<Double> generateHanningWindowsCoefficients(List<Double> lowPassFilterCoefficients, Double M) {
        List<Double> coefficients = new ArrayList<>();
        Double windowValue;
        for (int n = 0; n < lowPassFilterCoefficients.size(); n++) {
            windowValue = 0.5d - 0.5d * cos(2.d * PI * n / M);
            coefficients.add(lowPassFilterCoefficients.get(n) * windowValue);
        }
        return coefficients;
    }
}

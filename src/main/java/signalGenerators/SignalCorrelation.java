package signalGenerators;

import java.util.ArrayList;
import java.util.List;

public class SignalCorrelation {
    public List<Point> correlation(List<Point> firstPoints, List<Point> secondPoints, Double timeStep) {
        List<Point> invertedSecondPoints = new ArrayList<>();

        for (int i = secondPoints.size() - 1; i >= 0; i--) {
            invertedSecondPoints.add(secondPoints.get(i));
        }

        SignalConvolution signalConvolution = new SignalConvolution();
        return signalConvolution.convolution(firstPoints, invertedSecondPoints, timeStep);
    }
}

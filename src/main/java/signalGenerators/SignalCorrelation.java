package signalGenerators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SignalCorrelation {
    public List<Point> correlation(List<Point> firstPoints, List<Point> secondPoints, Double timeStep) {
        List<Point> invertedSecondPoints = new ArrayList<>(secondPoints);
        Collections.reverse(invertedSecondPoints);

        SignalConvolution signalConvolution = new SignalConvolution();
        return signalConvolution.convolution(firstPoints, invertedSecondPoints, timeStep);
    }
}

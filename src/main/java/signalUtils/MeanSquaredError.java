package signalUtils;

import java.util.List;

import signalGenerators.Point;

public class MeanSquaredError {

    public static Double getError(List<Point> orginalSignal, List<Point> processedSignal) {
        Double error = 0.d;

        for (int i = 0; i < processedSignal.size(); i++) {
            Double y1 = processedSignal.get(i).getY();
            Double y2 = orginalSignal.get(i).getY();
            error += Math.pow(y1 - y2, 2.d);
        }
        error = error / (double) processedSignal.size();
        return error;
    }
}
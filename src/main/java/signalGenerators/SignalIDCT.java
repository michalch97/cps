package signalGenerators;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;
import static java.lang.Math.sqrt;

public class SignalIDCT {
    public List<Point> inverse(List<Point> points, long[] execution) {
        List<Point> pointsAfterInversion = new ArrayList<>();
        int N = points.size();
        execution[0] = System.currentTimeMillis();
        for (int m = 0; m < N; m++) {
            pointsAfterInversion.add(inversePoint(points, N, m));
        }
        execution[1] = System.currentTimeMillis();
        return pointsAfterInversion;
    }

    private Point inversePoint(List<Point> points, int N, int m) {
        Point point = new Point();
        point.setX(points.get(m).getX());

        double c;
        if (m == 0) {
            c = sqrt(1.d / (double) N);
        } else {
            c = sqrt(2.d / (double) N);
        }

        Double yPointValue = 0.d;
        for (int n = 0; n < N; n++) {
            yPointValue += c * points.get(n).getY() * cos((PI * (2.d * (double) n + 1.d) * (double) m) / (2.d * (double) N));
        }

        point.setY(yPointValue);

        return point;
    }
}

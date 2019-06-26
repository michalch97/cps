package signalGenerators;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;
import static java.lang.Math.PI;

public class SignalDCT {
    public List<Point> transform(List<Point> points, long[] execution) {
        List<Point> pointsAfterTransformation = new ArrayList<>();
        int N = points.size();
        execution[0] = System.currentTimeMillis();
        for (int m = 0; m < N; m++) {
            pointsAfterTransformation.add(transformPoint(points, N, m));
        }
        execution[1] = System.currentTimeMillis();
        return pointsAfterTransformation;
    }

    private Point transformPoint(List<Point> points, int N, int m) {
        Point point = new Point();
        point.setX(points.get(m).getX());

        Double yPointValue = 0.d;
        for (int n = 0; n < N; n++) {
            yPointValue += points.get(n).getY() * cos((PI * (2.d * (double) n + 1.d) * (double) m) / (2.d * (double) N));
        }

        if (m == 0) {
            point.setY(yPointValue * (sqrt(1.d / (double) N)));
        } else {
            point.setY(yPointValue * (sqrt(2.d / (double) N)));
        }

        return point;
    }
}

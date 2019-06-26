package signalGenerators;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class SignalDFT {

    public List<ComplexPoint> transform(List<Point> points, long[] execution) {
        List<ComplexPoint> pointsAfterTransformation = new ArrayList<>();
        int N = points.size();
        execution[0] = System.currentTimeMillis();
        for (int m = 0; m < N; m++) {
            pointsAfterTransformation.add(transformPoint(points, N, m));
        }
        execution[1] = System.currentTimeMillis();
        return pointsAfterTransformation;
    }

    private ComplexPoint transformPoint(List<Point> points, int N, int m) {
        ComplexPoint point = new ComplexPoint();
        point.setX(points.get(m).getX());

        Double yPointRealValue = 0.d;
        Double yPointImaginaryValue = 0.d;
        for (int n = 0; n < N; n++) {
            yPointRealValue += points.get(n).getY()*cos(2.0*PI*(double)m*(double)n/(double)N);
            yPointImaginaryValue += points.get(n).getY()*(-1.d)*sin(2.0*PI*(double)m*(double)n/(double)N);
        }

        point.setYReal(yPointRealValue);
        point.setYImaginary(yPointImaginaryValue);

        return point;
    }
}

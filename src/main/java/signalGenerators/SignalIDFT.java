package signalGenerators;

import org.apache.commons.math3.complex.Complex;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class SignalIDFT {
    private List<Complex> complexValues = new ArrayList<>();

    public List<Point> inverse(List<ComplexPoint> complexPoints, long[] execution) {
        List<Point> pointsAfterInversion = new ArrayList<>();

        int N = complexPoints.size();

        for (ComplexPoint fp : complexPoints) {
            complexValues.add(new Complex(fp.getYReal(),fp.getYImaginary()));
        }
        execution[0] = System.currentTimeMillis();
        for (int n = 0; n < N; n++) {
            pointsAfterInversion.add(inversePoint(complexPoints, N, n));
        }
        execution[1] = System.currentTimeMillis();
        return pointsAfterInversion;
    }

    private Point inversePoint(List<ComplexPoint> complexPoints, int N, int n) {
        Point point = new Point();
        point.setX(complexPoints.get(n).getX());

        Complex value = Complex.ZERO;
        for (int m = 0; m < N; m++) {
            value = value.add(complexValues.get(m).multiply(new Complex(cos(2.0*PI*(double)m*(double)n/(double)N),sin(2.0*PI*(double)m*(double)n/(double)N))));
        }
        point.setY(value.getReal()/(double)N);
        return point;
    }
}

package signalGenerators;

import org.apache.commons.math3.complex.Complex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.*;

public class SignalIFCT {
    private List<Complex> complexValues = new ArrayList<>();
    private List<Complex> idftValues;

    public List<Point> inverse(List<Point> points, long[] execution) {
        int N = points.size();
        List<Point> pointsAfterInversion = new ArrayList<>();
        for (Point p : points) {
            complexValues.add(new Complex(p.getY(), 0));
        }
        execution[0] = System.currentTimeMillis();
        idftValues = idft(points, N);
        for (int n = 0; n < N; n++) {
            pointsAfterInversion.add(inversePoint(points, N, n));
        }
        execution[1] = System.currentTimeMillis();
        List<Point> signalAfterIFCT = createSignalAfterIFCT(pointsAfterInversion);

        return signalAfterIFCT;
    }

    private List<Point> createSignalAfterIFCT(List<Point> y) {
        int N = y.size();
        List<Point> x = Arrays.asList(new Point[N]);

        for (int n = 0; n < N / 2; n++) {
            x.set(2 * n, y.get(n));
            x.set(2 * n + 1,y.get(N - 1 - n));
        }

        if (N % 2 != 0) {
            x.set(N - 1, y.get(N / 2));
        }

        return x;
    }

    private List<Complex> idft(List<Point> points, int N) {
        List<Complex> complexList = new ArrayList<>();
        Complex value;
        for (int n = 0; n < N; n++) {
            value = Complex.ZERO;
            for (int m = 0; m < N; m++) {
                value = value.add(complexValues.get(m).multiply(new Complex(cos(2.0 * PI * (double) m * (double) n / (double) N), sin(2.0 * PI * (double) m * (double) n / (double) N))));
            }
            complexList.add(value);
        }
        return complexList;
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

        Complex e = new Complex(cos(PI * (double) m / (2.0 * (double) N)), sin(PI * (double) m / (2.0 * (double) N)));
        Double yPointValue = 0.d;
        yPointValue = calculatePoint(c, e, m);

        point.setY(yPointValue);

        return point;
    }

    private Double calculatePoint(double c, Complex e, int n) {
        Complex complexValue = idftValues.get(n).multiply(e).multiply(c);
        return complexValue.getReal();
    }
}

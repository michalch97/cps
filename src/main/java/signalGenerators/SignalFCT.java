package signalGenerators;

import org.apache.commons.math3.complex.Complex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.*;
import static java.lang.Math.sqrt;

public class SignalFCT {
    private List<Complex> dftValues;

    public List<Point> transform(List<Point> points, long[] execution) {
        List<Point> signalForFTC = createSignalForFCT(points);
        List<Point> pointsAfterTransformation = new ArrayList<>();
        int N = signalForFTC.size();
        execution[0] = System.currentTimeMillis();
        dftValues = dft(signalForFTC, N);
        for (int m = 0; m < N; m++) {
            pointsAfterTransformation.add(transformPoint(signalForFTC, N, m));
        }
        execution[1] = System.currentTimeMillis();
        return pointsAfterTransformation;
    }

    private List<Complex> dft(List<Point> points, int N) {
        List<Complex> complexList = new ArrayList<>();
        Double yPointRealValue;
        Double yPointImaginaryValue;
        for (int m = 0; m < N; m++) {
            yPointRealValue = 0.d;
            yPointImaginaryValue = 0.d;
            for (int n = 0; n < N; n++) {
                yPointRealValue += points.get(n).getY() * cos(2.0 * PI * (double) m * (double) n / (double) N);
                yPointImaginaryValue += points.get(n).getY() *(-1)*sin(2.0 * PI * (double) m * (double) n / (double) N);
            }
            complexList.add(new Complex(yPointRealValue,yPointImaginaryValue));
        }
        return complexList;
    }

    private Point transformPoint(List<Point> points, int N, int m) {
        Point point = new Point();
        point.setX(points.get(m).getX());

        double c;
        if (m == 0) {
            c = sqrt(1.d / (double) N);
        } else {
            c = sqrt(2.d / (double) N);
        }

        Complex e = new Complex(cos(PI * (double) m / (2.0 * (double) N)), (-1)*sin(PI * (double) m / (2.0 * (double) N)));
        Double yPointValue = 0.d;
        yPointValue = calculatePoint(c, e, m);

        point.setY(yPointValue);

        return point;
    }

    private Double calculatePoint(double c, Complex e, int n) {
        Complex complexValue = dftValues.get(n).multiply(e).multiply(c);
        return complexValue.getReal();
    }

    private List<Point> createSignalForFCT(List<Point> x) {
        int N = x.size();
        List<Point> y = Arrays.asList(new Point[N]);

        for (int n = 0; n < N / 2; n++) {
            y.set(n, x.get(2 * n));
            y.set(N - 1 - n, x.get(2 * n + 1));
        }

        if (N % 2 != 0) {
            y.set(N / 2, x.get(N - 1));
        }

        return y;
    }
}

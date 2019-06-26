package signalGenerators;

import org.apache.commons.math3.complex.Complex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.*;
import static java.lang.Math.PI;

public class SignalFFT {

    private int N;

    public List<ComplexPoint> transform(List<Point> points, long[] execution) {
        List<Point> oddPoints = new ArrayList<>();
        List<Point> evenPoints = new ArrayList<>();
        this.N = (points.size() % 2 == 0) ? points.size() : points.size() - 1;
        List<ComplexPoint> pointsAfterTransformation = Arrays.asList(new ComplexPoint[N]);
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                evenPoints.add(points.get(i));
            } else {
                oddPoints.add(points.get(i));
            }
        }
        execution[0] = System.currentTimeMillis();
        List<Complex> oddPointsAfterTransformation = transformOdd(oddPoints);
        List<Complex> evenPointsAfterTransformation = transformEven(evenPoints);
        for (int i = 0; i < N/2; i++) {
            Complex complexValue = evenPointsAfterTransformation.get(i).add(oddPointsAfterTransformation.get(i));
            Double yReal = complexValue.getReal();
            Double yImaginary = complexValue.getImaginary();
            pointsAfterTransformation.set(i,new ComplexPoint(points.get(i).getX(),yReal,yImaginary));
            complexValue = evenPointsAfterTransformation.get(i).subtract(oddPointsAfterTransformation.get(i));
            yReal = complexValue.getReal();
            yImaginary = complexValue.getImaginary();
            pointsAfterTransformation.set(i+N/2,new ComplexPoint(points.get(i+N/2).getX(),yReal,yImaginary));
        }
        execution[1] = System.currentTimeMillis();
        return pointsAfterTransformation;
    }

    private List<Complex> transformOdd(List<Point> oddPoints) {
        int N = oddPoints.size();
        List<Complex> pointsAfterTransformation = new ArrayList<>();
        for (int m = 0; m < N; m++) {
            pointsAfterTransformation.add(transformOddPoint(oddPoints, N, m));
        }
        return pointsAfterTransformation;
    }

    private List<Complex> transformEven(List<Point> evenPoints) {
        int N = evenPoints.size();
        List<Complex> pointsAfterTransformation = new ArrayList<>();
        for (int m = 0; m < N; m++) {
            pointsAfterTransformation.add(transformEvenPoint(evenPoints, N, m));
        }
        return pointsAfterTransformation;
    }

    private Complex transformEvenPoint(List<Point> points, int N, int m) {
        Double yPointRealValue = 0.d;
        Double yPointImaginaryValue = 0.d;
        for (int n = 0; n < N; n++) {
            yPointRealValue += points.get(n).getY()*cos(2.0*PI*(double)m*(double)n/(double)N);
            yPointImaginaryValue += points.get(n).getY()*(-1.d)*sin(2.0*PI*(double)m*(double)n/(double)N);
        }

        Complex point = new Complex(yPointRealValue,yPointImaginaryValue);

        return point;
    }

    private Complex transformOddPoint(List<Point> points, int N, int m) {
        Double yPointRealValue = 0.d;
        Double yPointImaginaryValue = 0.d;
        for (int n = 0; n < N; n++) {
            yPointRealValue += points.get(n).getY()*cos(2.0*PI*(double)m*(double)n/(double)N);
            yPointImaginaryValue += points.get(n).getY()*(-1.d)*sin(2.0*PI*(double)m*(double)n/(double)N);
        }

        Complex complex = new Complex(yPointRealValue,yPointImaginaryValue);
        Complex e = new Complex(cos(2.d*PI*(double)m/(double)this.N),(-1.d)*sin(2.d*PI*(double)m/(double)this.N));
        complex = e.multiply(complex);

        return complex;
    }
}

package signalGenerators;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class SignalOperations {
    private List<Point> points;
    private List<Point> pom;
    private Integer start;
    private Integer end;
    private Double sign;

    public List<Point> add(List<Point> signal1, List<Point> signal2, Double step) {

        if (!init(signal1, signal2, step))
            return new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            points.get(i).setY(points.get(i).getY() + pom.get(i + start).getY());
        }

        return points;
    }

    public List<Point> subtract(List<Point> signal1, List<Point> signal2, Double step) {

        if (!init(signal1, signal2, step))
            return new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            points.get(i).setY(sign * (points.get(i).getY() - pom.get(i + start).getY()));
        }

        return points;
    }

    public List<Point> multiply(List<Point> signal1, List<Point> signal2, Double step) {

        if (!init(signal1, signal2, step))
            return new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            points.get(i).setY(points.get(i).getY() * pom.get(i + start).getY());
        }

        return points;
    }

    public List<Point> divide(List<Point> signal1, List<Point> signal2, Double step) {

        if (!init(signal1, signal2, step))
            return new ArrayList<>();
        if (sign > 0.d) {
            for (int i = 0; i < points.size(); i++) {
                if (pom.get(i + start).getY() == 0.d) {
                    Double zeroCase = (points.get(i).getY() > 0.d) ? Double.MAX_VALUE : -Double.MAX_VALUE;
                    points.get(i).setY(zeroCase);
                }
                else{
                    points.get(i).setY(points.get(i).getY() / pom.get(i + start).getY());
                }
            }
        } else {
            for (int i = 0; i < points.size(); i++) {
                if (points.get(i).getY() == 0.d) {
                    Double zeroCase = (pom.get(i + start).getY() > 0.d) ? Double.MAX_VALUE : -Double.MAX_VALUE;
                    points.get(i).setY(zeroCase);
                }
                else{
                    points.get(i).setY(pom.get(i + start).getY() / points.get(i).getY());
                }
            }
        }

        return points;
    }

    private List<Point> copySignal(List<Point> source, List<Point> destination, int start, int end) {
        for (int i = start; i <= end; i++) {
            destination.add(new Point(source.get(i).getX(), source.get(i).getY()));
        }
        return destination;
    }

    private boolean init(List<Point> signal1, List<Point> signal2, Double step) {
        Double signal1Start = signal1.get(0).getX();
        Double signal2Start = signal2.get(0).getX();
        Double signal1End = signal1.get(signal1.size() - 1).getX();
        Double signal2End = signal2.get(signal2.size() - 1).getX();
        if (signal2End < signal1Start || signal1End < signal2Start)//sprawdzanie czesci wspolnej
            return false;
        Integer precision = BigDecimal.valueOf(step).scale();
        points = new ArrayList<>();
        start = 0;
        Boolean whichSignalStart = signal1Start < signal2Start;
        Boolean whichSignalEnd = signal1End < signal2End;
        if (whichSignalStart) {
            while (BigDecimal.valueOf(signal2Start).setScale(precision, RoundingMode.HALF_DOWN).doubleValue()
                    != BigDecimal.valueOf(signal1.get(start).getX()).setScale(precision, RoundingMode.HALF_DOWN).doubleValue()) {
                start++;
            }
            end = signal1.size() - 1;
            if (whichSignalEnd) {
                copySignal(signal1, points, start, end);
            } else {
                while (BigDecimal.valueOf(signal2End).setScale(precision, RoundingMode.HALF_DOWN).doubleValue()
                        != BigDecimal.valueOf(signal1.get(end).getX()).setScale(precision, RoundingMode.HALF_DOWN).doubleValue()) {
                    end--;
                }
            }
            sign = 1.d;
            pom = signal2;
        } else {
            while (BigDecimal.valueOf(signal1Start).setScale(precision, RoundingMode.HALF_DOWN).doubleValue()
                    != BigDecimal.valueOf(signal2.get(start).getX()).setScale(precision, RoundingMode.HALF_DOWN).doubleValue()) {
                start++;
            }
            end = signal2.size() - 1;
            if (whichSignalEnd) {
                while (BigDecimal.valueOf(signal1End).setScale(precision, RoundingMode.HALF_DOWN).doubleValue()
                        != BigDecimal.valueOf(signal2.get(end).getX()).setScale(precision, RoundingMode.HALF_DOWN).doubleValue()) {
                    end--;
                }

            } else {
                copySignal(signal2, points, start, end);
            }
            sign = -1.d;
            pom = signal1;
        }
        return true;
    }

}

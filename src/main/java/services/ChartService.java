package services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import signalGenerators.ComplexPoint;
import signalGenerators.Point;
import signalGenerators.SignalGenerator;
import signalGenerators.SignalGeneratorFourier;
import signalUtils.SignalParameters;
import signals.FixedSignal;
import simplify.Simplify;
import viewItems.SignalView;

@Getter
public class ChartService extends Service {

    private static final String fxmlChartWindowFileName = "fxml/chartWindow.fxml";
    private SignalView selectedItem;
    private List<Point> points;
    private List<ComplexPoint> pointsFourier;
    private boolean isLogarithmic;
    private boolean isFourier;

    public ChartService(SignalView selectedItem) {
        this.selectedItem = selectedItem;
        calculatePoints();

        Stage dialog = new Stage();
        createScene(dialog, fxmlChartWindowFileName);
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.setTitle("Wykres " + selectedItem.getName());
        stage.show();
    }

    private void calculatePoints() {
        if(selectedItem.getSignal() instanceof FixedSignal){
            this.isFourier = ((FixedSignal) selectedItem.getSignal()).isFourier();
        } else {
            this.isFourier = false;
        }

        if (isFourier) {
            SignalGeneratorFourier signalGenerator = new SignalGeneratorFourier(selectedItem.getSignal());
            pointsFourier = signalGenerator.generateSignal();
        } else {
            SignalParameters signalParameters = selectedItem.getSignalParameters();
            SignalGenerator signalGenerator = new SignalGenerator(selectedItem.getSignal(), signalParameters.getStartTime(), signalParameters.getDuration(), 0.00001d);

            points = signalGenerator.generateSignal();
            points = filterValuesTooHighForChart(points);
            points = simplifyPoints(points);
        }
    }

    private List<Point> filterValuesTooHighForChart(List<Point> points) {
        double limitValue = 9e3;
        return points.stream()
                .map(point -> {
                    if (point.getY() > limitValue) {
                        isLogarithmic = true;
                        return new Point(point.getX(), limitValue);
                    } else if (point.getY() < -limitValue) {
                        isLogarithmic = true;
                        return new Point(point.getX(), -limitValue);
                    } else {
                        return point;
                    }
                })
                .collect(Collectors.toList());
    }

    private List<Point> simplifyPoints(List<Point> points) {
        if (points.size() > 10_000) {
            Simplify<Point> simplify = new Simplify<>(new Point[0]);
            Point[] simplifiedPoints = simplify.simplify(points.toArray(new Point[0]), 0.00001, true);

            return Arrays.asList(simplifiedPoints);
        } else {
            return points;
        }
    }
}

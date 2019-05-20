package services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import signalGenerators.Point;
import signalGenerators.SignalGenerator;
import signalUtils.SignalParameters;
import simplify.Simplify;
import viewItems.SignalView;

@Getter
public class ChartService extends Service {

    private static final String fxmlChartWindowFileName = "fxml/chartWindow.fxml";
    private SignalView selectedItem;
    private List<Point> points;
    private boolean isLogarithmic;

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
        SignalParameters signalParameters = selectedItem.getSignalParameters();
        SignalGenerator signalGenerator = new SignalGenerator(selectedItem.getSignal(), signalParameters.getStartTime(), signalParameters.getDuration(), 0.00001d);

        points = signalGenerator.generateSignal();
        points = filterValuesTooHighForChart(points);
        points = simplifyPoints(points);
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
        Simplify<Point> simplify = new Simplify<>(new Point[0]);
        Point[] simplifiedPoints = simplify.simplify(points.toArray(new Point[0]), 0.00001, true);

        return Arrays.asList(simplifiedPoints);
    }
}

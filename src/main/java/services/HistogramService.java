package services;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import signalGenerators.Point;
import signalGenerators.SignalGenerator;
import signalUtils.SignalParameters;
import viewItems.HistogramClass;
import viewItems.SignalView;

@Getter
public class HistogramService extends Service {

    private static final String fxmlHistogramWindowFileName = "fxml/histogramWindow.fxml";
    private SignalView selectedItem;
    ObjectProperty<Integer> classCount;
    private List<HistogramClass> histogramValues;

    public HistogramService(SignalView selectedItem) {
        this.selectedItem = selectedItem;
        classCount = new SimpleObjectProperty<>(10);
        generateHistogram();

        Stage dialog = new Stage();
        createScene(dialog, fxmlHistogramWindowFileName);
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.setTitle("Histogram " + selectedItem.getName());
        stage.show();
    }

    public void generateHistogram() {
        SignalParameters signalParameters = selectedItem.getSignalParameters();
        SignalGenerator signalGenerator = new SignalGenerator(selectedItem.getSignal(), signalParameters.getStartTime(), signalParameters.getDuration(), 0.01d);

        List<Point> points = signalGenerator.generateSignal();
        if (points.size() > 0) {

            Double maxValue = points.stream().map(Point::getY).max(Double::compareTo).get();
            Double minValue = points.stream().map(Point::getY).min(Double::compareTo).get();

            double valueSpan = maxValue - minValue;
            double classSpan = valueSpan / classCount.get();

            Map<Integer, Long> classCounts = points.stream()
                                                   .map(Point::getY)
                                                   .map(value -> (value - minValue) / classSpan)
                                                   .map(value -> (int) Math.round(value))
                                                   .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

            DecimalFormat df = new DecimalFormat("#0.00");
            List<String> classLabels = IntStream.range(0, classCount.get())
                                                .mapToObj(classNumber -> df.format(classSpan * classNumber + minValue) + "_" +
                                                                         df.format(minValue + classSpan * (classNumber + 1)))
                                                .collect(Collectors.toList());

            List<HistogramClass> classPopulations = new ArrayList<>();
            for(int i = 0; i < classCount.get(); i++) {
                Long classPopulation = classCounts.get(i);
                String label = classLabels.get(i);
                classPopulations.add(new HistogramClass(label, classPopulation));
            }

            histogramValues = classPopulations;
        }
    }
}

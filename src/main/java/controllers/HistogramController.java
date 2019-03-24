package controllers;

import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import services.HistogramService;
import viewItems.HistogramClass;

public class HistogramController implements ServiceBindable<HistogramService> {
    public Spinner<Integer> SectionCountSpinner;
    public BarChart<String, Long> barChart;

    private HistogramService histogramService;

    @Override
    public void setService(HistogramService service) {
        histogramService = service;

        initHistogramChart();
        initSectionCountControllers();
    }

    private void initHistogramChart() {
        List<HistogramClass> histogramValues = histogramService.getHistogramValues();

        BarChart.Series<String, Long> series = new BarChart.Series<>();

        List<Data<String, Long>> dataPoints = histogramValues.stream()
                                                             .map(valueClass -> new Data<>(valueClass.getClassLabel(), valueClass.getClassPopulation()))
                                                             .collect(Collectors.toList());
        series.getData().addAll(dataPoints);

        barChart.setAnimated(false);
        barChart.getData().clear();
        barChart.getData().add(series);
        barChart.setLegendVisible(false);
    }

    private void initSectionCountControllers() {
        SectionCountSpinner.setValueFactory(new IntegerSpinnerValueFactory (5, 25, 10, 5));
        SectionCountSpinner.getValueFactory().valueProperty().bindBidirectional(histogramService.getClassCount());

        histogramService.getClassCount().addListener(observable -> {
            histogramService.generateHistogram();
            initHistogramChart();
        });
    }
}

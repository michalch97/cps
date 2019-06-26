package controllers;

import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import services.ChartService;
import signalGenerators.ComplexPoint;
import signalGenerators.Point;
import signalUtils.SignalStorageType;

public class ChartController implements ServiceBindable<ChartService> {
    public XYChart<Double, Double> lineChart;
    public XYChart<Double, Double> lineChartImaginary;
    public BorderPane ChartDialog;
    private ChartService chartService;

    @Override
    public void setService(ChartService service) {
        chartService = service;

        initChartConfig();
        initChart();
    }

    private void initChart() {

        if (chartService.isFourier()) {
            lineChartImaginary.setVisible(true);
            lineChartImaginary.setTitle("Część urojona");
            lineChart.setTitle("Część rzeczywista");

            List<ComplexPoint> points = chartService.getPointsFourier();
            XYChart.Series<Double, Double> seriesReal = new XYChart.Series<>();
            XYChart.Series<Double, Double> seriesImaginary = new XYChart.Series<>();

            List<Data<Double, Double>> dataPointsReal = points.stream().map(point -> new Data<>(point.getX(), point.getYReal())).collect(Collectors.toList());
            List<Data<Double, Double>> dataPointsImaginary = points.stream().map(point -> new Data<>(point.getX(), point.getYImaginary())).collect(Collectors.toList());
            seriesReal.getData().addAll(dataPointsReal);
            seriesImaginary.getData().addAll(dataPointsImaginary);
            lineChart.getData().add(seriesReal);
            lineChartImaginary.getData().add(seriesImaginary);
            seriesReal.getNode().setStyle("-fx-stroke: transparent;");
            seriesImaginary.getNode().setStyle("-fx-stroke: transparent;");

        } else {
            lineChart.prefHeightProperty().bind(ChartDialog.heightProperty());

            List<Point> points = chartService.getPoints();
            XYChart.Series<Double, Double> series = new XYChart.Series<>();

            List<Data<Double, Double>> dataPoints = points.stream().map(point -> new Data<>(point.getX(), point.getY())).collect(Collectors.toList());
            series.getData().addAll(dataPoints);

            lineChart.getData().add(series);
            if (chartService.getSelectedItem().getSignal().getSignalStorageType() == SignalStorageType.Discrete) {
                series.getNode().setStyle("-fx-stroke: transparent;");
            } else {
                hidePoints(dataPoints);
            }
        }
    }

    private void initChartConfig() {
        lineChart.setLegendVisible(false);
        lineChartImaginary.setLegendVisible(false);
        lineChartImaginary.setVisible(false);
    }

    private void hidePoints(List<Data<Double, Double>> dataPoints) {
        dataPoints.forEach(data -> {
            Region region = new Region();
            region.setShape(new Circle(0.d));
            region.setVisible(false);
            data.setNode(region);
        });
    }
}

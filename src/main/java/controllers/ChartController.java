package controllers;

import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import services.ChartService;
import signalGenerators.Point;
import signalUtils.SignalStorageType;

public class ChartController implements ServiceBindable<ChartService>  {
    public XYChart<Double, Double> lineChart;

    private ChartService chartService;

    @Override
    public void setService(ChartService service) {
        chartService = service;

        initChartConfig();
        initChart();
    }

    private void initChart() {
        List<Point> points = chartService.getPoints();
        XYChart.Series<Double, Double> series = new XYChart.Series<>();

        List<Data<Double, Double>> dataPoints = points.stream().map(point -> new Data<>(point.getX(), point.getY())).collect(Collectors.toList());
        series.getData().addAll(dataPoints);

        lineChart.getData().add(series);
        if (chartService.getSelectedItem().getSignal().getSignalStorageType() == SignalStorageType.Discrete) {
            series.getNode().setStyle("-fx-stroke: transparent;");
        } else {
            setPointsSize(dataPoints, 0.5d);
        }
    }

    private void initChartConfig() {
        lineChart.setLegendVisible(false);
    }

    private void setPointsSize(List<Data<Double, Double>> dataPoints, Double size) {
        dataPoints.forEach(data ->  {
            Region region = new Region();
            region.setShape(new Circle(size));
            data.setNode(region);
        });
    }
}

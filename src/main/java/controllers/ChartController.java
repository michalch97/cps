package controllers;

import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import services.ChartService;
import signalGenerators.Point;

public class ChartController implements ServiceBindable<ChartService>  {
    public LineChart<Double, Double> lineChart;

    private ChartService chartService;

    @Override
    public void setService(ChartService service) {
        chartService = service;

        initChart();
    }

    private void initChart() {
        initChartConfig();

        List<Point> points = chartService.getPoints();
        XYChart.Series<Double, Double> series = new XYChart.Series<>();

        List<Data<Double, Double>> dataPoints = points.stream().map(point -> new Data<>(point.getX(), point.getY())).collect(Collectors.toList());
        series.getData().addAll(dataPoints);

        lineChart.getData().add(series);
    }

    private void initChartConfig() {

    }
}

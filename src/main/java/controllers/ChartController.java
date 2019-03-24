package controllers;

import javafx.scene.chart.LineChart;
import services.ChartService;

public class ChartController implements ServiceBindable<ChartService>  {
    public LineChart lineChart;

    private ChartService chartService;

    @Override
    public void setService(ChartService service) {
        chartService = service;
    }
}

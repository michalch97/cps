package controllers;

import javafx.scene.chart.BarChart;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import services.HistogramService;

public class HistogramController implements ServiceBindable<HistogramService> {
    public Slider SectionCountSlider;
    public Spinner SectionCountSpinner;
    public BarChart barChart;

    private HistogramService histogramService;

    @Override
    public void setService(HistogramService service) {
        histogramService = service;
    }
}

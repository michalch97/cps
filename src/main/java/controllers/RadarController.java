package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.DoubleSpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.util.converter.DoubleStringConverter;
import radarSimulator.SimulatorSettingsDto;
import services.RadarService;

public class RadarController extends Controller implements ServiceBindable<RadarService> {
    public TextField SignalNameField;
    public Spinner<Double> EnvWaveSpinner;
    public Spinner<Integer> SensorSamplesSpinner;
    public Spinner<Double> SimTimeSpinner;
    public Spinner<Double> SignalPeriodSpinner;
    public Spinner<Integer> SignalSamplesPerSecondSpinner;
    public Spinner<Integer> BufferLengthSpinner;
    public Spinner<Double> StartDistanceSpinner;
    public Spinner<Double> ObjectSpeedSpinner;
    public Button CreateButton;
    public Button CancelButton;

    private RadarService service;

    @Override
    public void setService(RadarService service) {
        this.service = service;

        initSpinners();
        CreateButton.disableProperty().bind(SignalNameField.textProperty().isEmpty());
    }

    public void onCreateButtonClicked(ActionEvent event) {
        SimulatorSettingsDto settings = createSettings();
        service.createSimulation(settings, SignalNameField.getText());

        closeWindow(CreateButton);
    }

    public void onCancelButtonClicked(ActionEvent event) {
        closeWindow(CancelButton);
    }

    private void initSpinners() {
        DoubleSpinnerValueFactory valueFactory = new DoubleSpinnerValueFactory(Double.MIN_VALUE, Double.MAX_VALUE, 1_000.d, 1.d);
        valueFactory.setConverter(new DoubleStringConverter());
        EnvWaveSpinner.setValueFactory(valueFactory);
        SensorSamplesSpinner.setValueFactory(new IntegerSpinnerValueFactory(5, Integer.MAX_VALUE, 20, 1));

        DoubleSpinnerValueFactory simTimeValueFactory = new DoubleSpinnerValueFactory(1.d, Double.MAX_VALUE, 6.d, 1.d);
        SimTimeSpinner.setValueFactory(simTimeValueFactory);
        DoubleSpinnerValueFactory signalPeriodValueFactory = new DoubleSpinnerValueFactory(1.d, Double.MAX_VALUE, 3.d, 1.d);
        SignalPeriodSpinner.setValueFactory(signalPeriodValueFactory);
        SignalSamplesPerSecondSpinner.setValueFactory(new IntegerSpinnerValueFactory(10, Integer.MAX_VALUE, 300, 10));
        BufferLengthSpinner.setValueFactory(new IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 900, 1));

        DoubleSpinnerValueFactory startDistanceValueFactory = new DoubleSpinnerValueFactory(1.d, Double.MAX_VALUE, 500.d, 10.d);
        StartDistanceSpinner.setValueFactory(startDistanceValueFactory);
        DoubleSpinnerValueFactory objectSpeedValueFactory = new DoubleSpinnerValueFactory(1.d, Double.MAX_VALUE, 25.d, 5.d);
        ObjectSpeedSpinner.setValueFactory(objectSpeedValueFactory);
    }

    private SimulatorSettingsDto createSettings() {
        return SimulatorSettingsDto.builder()
                                   .waveEnvironmentalSpeed(EnvWaveSpinner.getValue())
                                   .sensorSamplesPerSecond(SensorSamplesSpinner.getValue())
                                   .simulationTime(SimTimeSpinner.getValue())
                                   .signalPeriod(SignalPeriodSpinner.getValue())
                                   .signalSamplesPerSecond(SignalSamplesPerSecondSpinner.getValue())
                                   .bufferLength(BufferLengthSpinner.getValue())
                                   .objectStartDistance(StartDistanceSpinner.getValue())
                                   .objectSpeed(ObjectSpeedSpinner.getValue())
                                   .build();
    }
}

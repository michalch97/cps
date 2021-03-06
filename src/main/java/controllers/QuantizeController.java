package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.DoubleSpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.util.converter.DoubleStringConverter;
import services.QuantizeSignalService;

public class QuantizeController extends Controller implements ServiceBindable<QuantizeSignalService> {
    public TextField SignalNameField;
    public Spinner<Double> FrequencySpinner;
    public Spinner<Integer> StepCountSpinner;
    public Button CreateButton;
    public Button CancelButton;

    private QuantizeSignalService service;

    @Override
    public void setService(QuantizeSignalService service) {
        this.service = service;

        initSpinners();
        CreateButton.disableProperty().bind(SignalNameField.textProperty().isEmpty());
    }

    public void onCreateButtonClicked(ActionEvent event) {
        Double timeStep = FrequencySpinner.getValue();
        Integer quantumStepCount = StepCountSpinner.getValue();
        String signalName = SignalNameField.getText();

        service.createQuantizedSignal(signalName, timeStep, quantumStepCount);
        closeWindow(CreateButton);

    }

    public void onCancelButtonClicked(ActionEvent event) {
        closeWindow(CancelButton);
    }

    private void initSpinners() {
        DoubleSpinnerValueFactory valueFactory = new DoubleSpinnerValueFactory(Double.MIN_VALUE, Double.MAX_VALUE, 0.1d, 0.1d);
        valueFactory.setConverter(new DoubleStringConverter());
        FrequencySpinner.setValueFactory(valueFactory);

        StepCountSpinner.setValueFactory(new IntegerSpinnerValueFactory(2, Integer.MAX_VALUE, 2, 1));
        FrequencySpinner.setEditable(true);
        StepCountSpinner.setEditable(true);
    }
}

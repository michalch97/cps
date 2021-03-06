package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.DoubleSpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.util.converter.DoubleStringConverter;
import services.SampleSignalService;

public class SampleController extends Controller implements ServiceBindable<SampleSignalService> {
    public TextField SignalNameField;
    public Spinner<Double> FrequencySpinner;
    public Button CreateButton;
    public Button CancelButton;

    private SampleSignalService service;

    @Override
    public void setService(SampleSignalService service) {
        this.service = service;

        initSpinners();
        CreateButton.disableProperty().bind(SignalNameField.textProperty().isEmpty());
    }

    public void onCreateButtonClicked(ActionEvent event) {
        Double frequency = FrequencySpinner.getValue();
        String signalName = SignalNameField.getText();

        service.createSampledSignal(signalName, frequency);
        closeWindow(CreateButton);
    }

    public void onCancelButtonClicked(ActionEvent event) {
        closeWindow(CancelButton);
    }

    private void initSpinners() {
        DoubleSpinnerValueFactory valueFactory = new DoubleSpinnerValueFactory(Double.MIN_VALUE, Double.MAX_VALUE, 0.001d, 0.1d);
        valueFactory.setConverter(new DoubleStringConverter());

        FrequencySpinner.setValueFactory(valueFactory);
        FrequencySpinner.setEditable(true);
    }
}

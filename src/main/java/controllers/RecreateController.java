package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.DoubleSpinnerValueFactory;
import javafx.scene.control.TextField;
import services.RecreateSignalService;

public class RecreateController extends Controller implements ServiceBindable<RecreateSignalService> {
    public TextField newSignalName;
    public Label SignalName;
    public Spinner<Double> InterpolationSpinner;
    public Button SaveButton;
    public Button CancelButton;

    private RecreateSignalService service;

    @Override
    public void setService(RecreateSignalService service) {
        this.service = service;

        initSignalNames();
    }

    public void onSaveClicked(ActionEvent event) {
        String signalName = newSignalName.getText();
        Double timeStep = InterpolationSpinner.getValue();
        service.recreateSignal(signalName, timeStep);
        closeWindow(SaveButton);
    }

    public void onCancelClicked(ActionEvent event) {
        closeWindow(CancelButton);
    }

    private void initSignalNames() {
        SignalName.setText(service.getSignalView().getName());
        InterpolationSpinner.setValueFactory(new DoubleSpinnerValueFactory(Double.MIN_VALUE, Double.MAX_VALUE, 0.1d, 0.1d));

        SaveButton.disableProperty().bind(SignalName.textProperty().isEmpty());
    }
}

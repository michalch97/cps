package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.FFTService;

public class FFTDialogController extends Controller implements ServiceBindable<FFTService> {
    public TextField SignalNameField;
    public Button CreateButton;
    public Button CancelButton;
    private FFTService FFTService;
    @Override
    public void setService(FFTService service) {
        this.FFTService = service;
        CreateButton.disableProperty().bind(SignalNameField.textProperty().isEmpty());
    }

    public void onCreateButtonClicked(ActionEvent actionEvent) {
        String signalName = SignalNameField.getText();
        FFTService.createSignalByFFT(signalName);
        closeWindow(CreateButton);
    }

    public void onCancelButtonClicked(ActionEvent actionEvent) {
        closeWindow(CancelButton);
    }
}

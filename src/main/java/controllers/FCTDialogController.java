package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.FCTService;

public class FCTDialogController extends Controller implements ServiceBindable<FCTService> {
    public TextField SignalNameField;
    public Button CreateButton;
    public Button CancelButton;
    private FCTService FCTService;

    @Override
    public void setService(FCTService service) {
        this.FCTService = service;
        CreateButton.disableProperty().bind(SignalNameField.textProperty().isEmpty());
    }

    public void onCreateButtonClicked(ActionEvent actionEvent) {
        String signalName = SignalNameField.getText();
        FCTService.createSignalByFCT(signalName);
        closeWindow(CreateButton);
    }

    public void onCancelButtonClicked(ActionEvent actionEvent) {
        closeWindow(CancelButton);
    }
}

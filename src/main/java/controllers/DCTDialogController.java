package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.DCTService;

public class DCTDialogController extends Controller implements ServiceBindable<DCTService> {
    public TextField SignalNameField;
    public Button CreateButton;
    public Button CancelButton;
    private DCTService DCTService;

    @Override
    public void setService(DCTService service) {
        this.DCTService = service;
        CreateButton.disableProperty().bind(SignalNameField.textProperty().isEmpty());
    }

    public void onCreateButtonClicked(ActionEvent actionEvent) {
        String signalName = SignalNameField.getText();
        DCTService.createSignalByDCT(signalName);
        closeWindow(CreateButton);
    }

    public void onCancelButtonClicked(ActionEvent actionEvent) {
        closeWindow(CancelButton);
    }
}

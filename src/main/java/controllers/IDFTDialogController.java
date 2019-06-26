package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.IDFTService;

public class IDFTDialogController extends Controller implements ServiceBindable<IDFTService> {
    public TextField SignalNameField;
    public Button CreateButton;
    public Button CancelButton;
    private IDFTService IDFTService;

    @Override
    public void setService(IDFTService service) {
        this.IDFTService = service;
        CreateButton.disableProperty().bind(SignalNameField.textProperty().isEmpty());
    }

    public void onCreateButtonClicked(ActionEvent actionEvent) {
        String signalName = SignalNameField.getText();
        IDFTService.createSignalByIDFT(signalName);
        closeWindow(CreateButton);
    }

    public void onCancelButtonClicked(ActionEvent actionEvent) {
        closeWindow(CancelButton);
    }
}

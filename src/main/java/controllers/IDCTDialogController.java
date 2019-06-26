package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.IDCTService;

public class IDCTDialogController extends Controller implements ServiceBindable<IDCTService> {
    public TextField SignalNameField;
    public Button CreateButton;
    public Button CancelButton;
    private IDCTService IDCTService;

    @Override
    public void setService(IDCTService service) {
        this.IDCTService = service;
        CreateButton.disableProperty().bind(SignalNameField.textProperty().isEmpty());
    }

    public void onCreateButtonClicked(ActionEvent actionEvent) {
        String signalName = SignalNameField.getText();
        IDCTService.createSignalByIDCT(signalName);
        closeWindow(CreateButton);
    }

    public void onCancelButtonClicked(ActionEvent actionEvent) {
        closeWindow(CancelButton);
    }
}

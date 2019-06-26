package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.DFTService;

public class DFTDialogController extends Controller implements ServiceBindable<DFTService> {
    public TextField SignalNameField;
    public Button CreateButton;
    public Button CancelButton;
    private DFTService DFTService;

    @Override
    public void setService(DFTService service) {
        this.DFTService = service;
        CreateButton.disableProperty().bind(SignalNameField.textProperty().isEmpty());
    }

    public void onCreateButtonClicked(ActionEvent actionEvent) {
        String signalName = SignalNameField.getText();
        DFTService.createSignalByDFT(signalName);
        closeWindow(CreateButton);
    }

    public void onCancelButtonClicked(ActionEvent actionEvent) {
        closeWindow(CancelButton);
    }
}

package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.IFCTService;

public class IFCTDialogController extends Controller implements ServiceBindable<IFCTService> {
    public TextField SignalNameField;
    public Button CreateButton;
    public Button CancelButton;
    private IFCTService IFCTService;
    @Override
    public void setService(IFCTService service) {
        this.IFCTService = service;
        CreateButton.disableProperty().bind(SignalNameField.textProperty().isEmpty());
    }

    public void onCreateButtonClicked(ActionEvent actionEvent) {
        String signalName = SignalNameField.getText();
        IFCTService.createSignalByIFCT(signalName);
        closeWindow(CreateButton);
    }

    public void onCancelButtonClicked(ActionEvent actionEvent) {
        closeWindow(CancelButton);
    }
}

package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.RecreateSignalService;

public class RecreateController extends Controller implements ServiceBindable<RecreateSignalService> {
    public TextField newSignalName;
    public Label SignalName;
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
        service.recreateSignal(signalName);
        closeWindow(SaveButton);
    }

    public void onCancelClicked(ActionEvent event) {
        closeWindow(CancelButton);
    }

    private void initSignalNames() {
        SignalName.setText(service.getSignalView().getName());

        SaveButton.disableProperty().bind(SignalName.textProperty().isEmpty());
    }
}

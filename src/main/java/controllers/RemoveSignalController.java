package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import services.RemoveSignalService;

public class RemoveSignalController implements ServiceBindable<RemoveSignalService> {
    public Label SignalName;
    public Button DeleteButton;
    public Button CancelButton;

    private RemoveSignalService removeSignalService;

    public void onRemoveClicked(ActionEvent actionEvent) {

    }

    public void onCancelClicked(ActionEvent actionEvent) {

    }

    @Override
    public void setService(RemoveSignalService service) {
        removeSignalService = service;
    }
}

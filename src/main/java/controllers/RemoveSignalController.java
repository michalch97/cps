package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import services.RemoveSignalService;

public class RemoveSignalController extends Controller implements ServiceBindable<RemoveSignalService> {
    public Label SignalName;
    public Button DeleteButton;
    public Button CancelButton;

    private RemoveSignalService removeSignalService;

    public void onRemoveClicked(ActionEvent actionEvent) {
        removeSignalService.removeSignal();
        closeWindow(DeleteButton);
    }

    public void onCancelClicked(ActionEvent actionEvent) {
        closeWindow(CancelButton);
    }

    @Override
    public void setService(RemoveSignalService service) {
        removeSignalService = service;
    }
}

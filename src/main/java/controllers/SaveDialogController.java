package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.SaveDialogService;

public class SaveDialogController implements ServiceBindable<SaveDialogService> {
    public Label SignalName;
    public TextField FilePathField;
    public Button ChooseFileButton;
    public Button SaveButton;
    public Button CancelButton;

    SaveDialogService saveDialogService;

    public void onChooseFileClicked(ActionEvent actionEvent) {

    }

    public void onSaveClicked(ActionEvent actionEvent) {

    }

    public void onCancelClicked(ActionEvent actionEvent) {

    }

    @Override
    public void setService(SaveDialogService service) {
        saveDialogService = service;
    }
}

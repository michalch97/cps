package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.LoadDialogService;

public class LoadDialogController implements ServiceBindable<LoadDialogService> {
    public TextField FilePathField;
    public Button ChooseFileButton;
    public Button LoadButton;
    public Button CancelButton;

    private LoadDialogService loadDialogService;

    public void onChooseFileClicked(ActionEvent actionEvent) {

    }

    public void onLoadClicked(ActionEvent actionEvent) {

    }

    public void onCancelClicked(ActionEvent actionEvent) {

    }

    @Override
    public void setService(LoadDialogService service) {
        loadDialogService = service;
    }
}

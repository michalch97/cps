package controllers;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import services.LoadDialogService;

public class LoadDialogController extends Controller implements ServiceBindable<LoadDialogService> {
    public TextField FilePathField;
    public Button ChooseFileButton;
    public Button LoadButton;
    public Button CancelButton;

    private LoadDialogService loadDialogService;

    public void onChooseFileClicked(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Wybierz plik");
        File file = chooser.showOpenDialog(ChooseFileButton.getScene().getWindow());

        FilePathField.setText(file.getAbsolutePath());
    }

    public void onLoadClicked(ActionEvent actionEvent) {
        String filePath = FilePathField.getText();
        loadDialogService.loadSignalFromFile(filePath);
        closeWindow(LoadButton);
    }

    public void onCancelClicked(ActionEvent actionEvent) {
        closeWindow(CancelButton);
    }

    @Override
    public void setService(LoadDialogService service) {
        loadDialogService = service;
    }
}

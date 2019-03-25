package controllers;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import services.SaveDialogService;
import viewItems.SignalView;

public class SaveDialogController extends Controller implements ServiceBindable<SaveDialogService> {
    public Label SignalName;
    public TextField FilePathField;
    public Button ChooseFileButton;
    public Button SaveButton;
    public Button CancelButton;

    private SaveDialogService saveDialogService;

    public void onChooseFileClicked(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Podaj plik");
        File file = chooser.showSaveDialog(ChooseFileButton.getScene().getWindow());

        if (file != null) {
            FilePathField.setText(file.getAbsolutePath());
        }
    }

    public void onSaveClicked(ActionEvent actionEvent) {
        saveDialogService.saveSignalToFile(FilePathField.getText());
        closeWindow(SaveButton);
    }

    public void onCancelClicked(ActionEvent actionEvent) {
        closeWindow(CancelButton);
    }

    @Override
    public void setService(SaveDialogService service) {
        saveDialogService = service;

        initDialog();
    }

    private void initDialog() {
        SignalView selectedItem = saveDialogService.getSelectedItem();
        SignalName.setText(selectedItem.getName());

        SaveButton.disableProperty().bind(FilePathField.textProperty().isEmpty());
    }
}

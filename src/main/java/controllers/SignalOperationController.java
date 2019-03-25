package controllers;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.SignalOperationService;
import viewItems.SignalView;

public class SignalOperationController extends Controller implements ServiceBindable<SignalOperationService> {
    public TextField newSignalName;
    public Label SignalName;
    public ComboBox<SignalView> SecondSignalComboBox;
    public Button SaveButton;
    public Button CancelButton;

    private SignalOperationService signalOperationService;

    public void onSaveClicked(ActionEvent actionEvent) {
        SignalView selectedItem = SecondSignalComboBox.getSelectionModel().getSelectedItem();
        String newName = newSignalName.getText();

        signalOperationService.createNewSignalByOperation(newName, selectedItem);
        closeWindow(SaveButton);
    }

    public void onCancelClicked(ActionEvent actionEvent) {
        closeWindow(CancelButton);
    }

    @Override
    public void setService(SignalOperationService service) {
        this.signalOperationService = service;

        initSignalNames();
    }

    private void initSignalNames() {
        SignalName.setText(signalOperationService.getSignalView().getName());

        List<SignalView> signalsToChooseFrom = signalOperationService.getSignalsToChooseFrom();
        ObservableList<SignalView> signals = FXCollections.observableArrayList(signalsToChooseFrom);
        SecondSignalComboBox.setItems(signals);

        SaveButton.disableProperty().bind(SecondSignalComboBox.valueProperty().isNull()
                                                              .or(newSignalName.textProperty().isEmpty()));
    }
}

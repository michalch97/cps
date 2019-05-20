package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.ConvolutionService;
import services.CorrelationService;
import viewItems.SignalView;

import java.util.List;

public class CorrelationController extends Controller implements ServiceBindable<CorrelationService> {
    public TextField newSignalName;
    public Label SignalName;
    public ComboBox<SignalView> SecondSignalComboBox;
    public Button SaveButton;
    public Button CancelButton;
    private CorrelationService correlationService;

    @Override
    public void setService(CorrelationService service) {
        this.correlationService = service;

        initSignalNames();
    }

    private void initSignalNames() {
        SignalName.setText(correlationService.getSignalView().getName());

        List<SignalView> signalsToChooseFrom = correlationService.getSignalsToChooseFrom();
        ObservableList<SignalView> signals = FXCollections.observableArrayList(signalsToChooseFrom);
        SecondSignalComboBox.setItems(signals);

        SaveButton.disableProperty().bind(SecondSignalComboBox.valueProperty().isNull()
                .or(newSignalName.textProperty().isEmpty()));
    }

    public void onSaveClicked(ActionEvent actionEvent) {
        SignalView selectedItem = SecondSignalComboBox.getSelectionModel().getSelectedItem();
        String newName = newSignalName.getText();

        correlationService.createNewSignalByCorrelation(newName, selectedItem);
        closeWindow(SaveButton);
    }

    public void onCancelClicked(ActionEvent actionEvent) {
        closeWindow(CancelButton);
    }
}

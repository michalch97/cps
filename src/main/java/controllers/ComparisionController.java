package controllers;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import services.CompareSignalsService;
import viewItems.SignalView;

public class ComparisionController extends Controller implements ServiceBindable<CompareSignalsService> {
    public Label SignalName;
    public ComboBox<SignalView> SecondSignalComboBox;
    public Button SaveButton;
    public Button CancelButton;

    private CompareSignalsService service;

    @Override
    public void setService(CompareSignalsService service) {
        this.service = service;

        initSignalNames();
    }

    public void onSaveClicked(ActionEvent event) {
    }

    public void onCancelClicked(ActionEvent event) {
        closeWindow(CancelButton);
    }

    private void initSignalNames() {
        SignalName.setText(service.getSignalView().getName());

        List<SignalView> signalsToChooseFrom = service.getSignalsToChooseFrom();
        ObservableList<SignalView> signals = FXCollections.observableArrayList(signalsToChooseFrom);
        SecondSignalComboBox.setItems(signals);

        SaveButton.disableProperty().bind(SecondSignalComboBox.valueProperty().isNull()
                                                              .or(SignalName.textProperty().isEmpty()));
    }
}

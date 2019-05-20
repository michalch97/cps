package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.ConvolutionService;
import viewItems.SignalView;

import java.util.List;

public class ConvolutionController extends Controller implements ServiceBindable<ConvolutionService>  {
    public TextField newSignalName;
    public Label SignalName;
    public ComboBox<SignalView> SecondSignalComboBox;
    public Button SaveButton;
    public Button CancelButton;
    private ConvolutionService convolutionService;

    @Override
    public void setService(ConvolutionService service) {
        this.convolutionService = service;

        initSignalNames();
    }

    private void initSignalNames() {
        SignalName.setText(convolutionService.getSignalView().getName());

        List<SignalView> signalsToChooseFrom = convolutionService.getSignalsToChooseFrom();
        ObservableList<SignalView> signals = FXCollections.observableArrayList(signalsToChooseFrom);
        SecondSignalComboBox.setItems(signals);

        SaveButton.disableProperty().bind(SecondSignalComboBox.valueProperty().isNull()
                .or(newSignalName.textProperty().isEmpty()));
    }

    public void onSaveClicked(ActionEvent actionEvent) {
        SignalView selectedItem = SecondSignalComboBox.getSelectionModel().getSelectedItem();
        String newName = newSignalName.getText();

        convolutionService.createNewSignalByConvolution(newName, selectedItem);
        closeWindow(SaveButton);
    }

    public void onCancelClicked(ActionEvent actionEvent) {
        closeWindow(CancelButton);
    }
}

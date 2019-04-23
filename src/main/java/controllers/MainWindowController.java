package controllers;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.MainWindowService;
import signalUtils.SignalOperationType;
import viewItems.SignalView;

public class MainWindowController implements ServiceBindable<MainWindowService> {
    public Button ShowChartButton;
    public Button ShowHistogramButton;
    public Button ShowParametersButton;

    public Button AdditionOperationButton;
    public Button MultiplicationOperationButton;
    public Button SubtractionOperationButton;
    public Button DivisionOperationButton;
    public TableView<SignalView> SignalsTableView;
    public TableColumn<SignalView, String> SignalName;
    public Button SaveSignalButton;
    public Button LoadSignalButton;
    public Button LoadSignalInTextButton;
    public Button AddSignalButton;
    public Button RemoveSignalButton;
    public Button SampleButton;
    public Button QuantizeButton;
    public Button RecreateButton;
    public Button CompareButton;

    MainWindowService windowService;

    public void onShowChartClicked(ActionEvent actionEvent) {
        SignalView selectedItem = SignalsTableView.getSelectionModel().getSelectedItem();
        windowService.showChart(selectedItem);
    }

    public void onShowHistogramClicked(ActionEvent actionEvent) {
        SignalView selectedItem = SignalsTableView.getSelectionModel().getSelectedItem();
        windowService.showHistogram(selectedItem);
    }

    public void onShowParametersClicked(ActionEvent actionEvent) {
        SignalView selectedItem = SignalsTableView.getSelectionModel().getSelectedItem();
        windowService.showSignalParameters(selectedItem);
    }


    public void onSaveSignalClicked(ActionEvent actionEvent) {
        SignalView selectedItem = SignalsTableView.getSelectionModel().getSelectedItem();
        windowService.openSaveDialog(selectedItem);
    }

    public void onLoadSignalClicked(ActionEvent actionEvent) {
        windowService.openLoadDialog();
    }

    public void onLoadTextSignalClicked(ActionEvent actionEvent) {
        windowService.openLoadTextDialog();
    }

    public void onAddNewSignalClicked(ActionEvent actionEvent) {
        windowService.openAddNewSignalDialog();
    }

    public void onRemoveSignalClicked(ActionEvent actionEvent) {
        SignalView selectedItem = SignalsTableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            windowService.openRemoveSignalDialog(selectedItem);
        }
    }


    public void onAdditionClicked(ActionEvent actionEvent) {
        operationSelected(SignalOperationType.ADDITION);
    }

    public void onMultiplicationClicked(ActionEvent actionEvent) {
        operationSelected(SignalOperationType.MULTIPLICATION);
    }

    public void onSubtractionClicked(ActionEvent actionEvent) {
        operationSelected(SignalOperationType.SUBTRACTION);
    }

    public void onDivisionClicked(ActionEvent actionEvent) {
        operationSelected(SignalOperationType.DIVISION);
    }

    private void operationSelected(SignalOperationType operationType) {
        SignalView selectedItem = SignalsTableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            windowService.openSignalOperationDialog(selectedItem, operationType);
        }
    }

    @Override
    public void setService(MainWindowService service) {
        windowService = service;

        initSignalsTableView();
        initSelectionBindings();
    }

    private void initSignalsTableView() {
        SignalName.setCellValueFactory(new PropertyValueFactory<>("name"));

        SignalsTableView.setItems(windowService.getSignals());
    }

    private void initSelectionBindings() {
        SaveSignalButton.disableProperty().bind(Bindings.isEmpty(SignalsTableView.getSelectionModel().getSelectedItems()));
        RemoveSignalButton.disableProperty().bind(Bindings.isEmpty(SignalsTableView.getSelectionModel().getSelectedItems()));
        SampleButton.disableProperty().bind(Bindings.isEmpty(SignalsTableView.getSelectionModel().getSelectedItems()));
        QuantizeButton.disableProperty().bind(Bindings.isEmpty(SignalsTableView.getSelectionModel().getSelectedItems()));
        RecreateButton.disableProperty().bind(Bindings.isEmpty(SignalsTableView.getSelectionModel().getSelectedItems()));
        CompareButton.disableProperty().bind(Bindings.isEmpty(SignalsTableView.getSelectionModel().getSelectedItems()));
        ShowChartButton.disableProperty().bind(Bindings.isEmpty(SignalsTableView.getSelectionModel().getSelectedItems()));
        ShowHistogramButton.disableProperty().bind(Bindings.isEmpty(SignalsTableView.getSelectionModel().getSelectedItems()));
        ShowParametersButton.disableProperty().bind(Bindings.isEmpty(SignalsTableView.getSelectionModel().getSelectedItems()));
        AdditionOperationButton.disableProperty().bind(Bindings.isEmpty(SignalsTableView.getSelectionModel().getSelectedItems()));
        MultiplicationOperationButton.disableProperty().bind(Bindings.isEmpty(SignalsTableView.getSelectionModel().getSelectedItems()));
        SubtractionOperationButton.disableProperty().bind(Bindings.isEmpty(SignalsTableView.getSelectionModel().getSelectedItems()));
        DivisionOperationButton.disableProperty().bind(Bindings.isEmpty(SignalsTableView.getSelectionModel().getSelectedItems()));
    }

    public void OnSampleClicked(ActionEvent event) {
        SignalView selectedItem = SignalsTableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            windowService.sampleSignal(selectedItem);
        }
    }

    public void OnQuantizeClicked(ActionEvent event) {
        SignalView selectedItem = SignalsTableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            windowService.quantizeSignal(selectedItem);
        }
    }

    public void OnRecreateClicked(ActionEvent event) {
        SignalView selectedItem = SignalsTableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            windowService.recreateSignal(selectedItem);
        }
    }

    public void OnCompareClicked(ActionEvent event) {
        SignalView selectedItem = SignalsTableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            windowService.compareSignal(selectedItem);
        }
    }
}

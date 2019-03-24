package controllers;

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

    MainWindowService windowService;

    public void onShowChartClicked(ActionEvent actionEvent) {
        windowService.showChart();
    }

    public void onShowHistogramClicked(ActionEvent actionEvent) {
        windowService.showHistogram();
    }

    public void onShowParametersClicked(ActionEvent actionEvent) {
        windowService.showSignalParameters();
    }


    public void onSaveSignalClicked(ActionEvent actionEvent) {
        windowService.openSaveDialog();
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
    }

    private void initSignalsTableView() {
        SignalName.setCellValueFactory(new PropertyValueFactory<>("name"));

        SignalsTableView.setItems(windowService.getSignals());
    }
}

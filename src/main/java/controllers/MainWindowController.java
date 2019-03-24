package controllers;

import exceptions.IncorrectServiceControllerBindingException;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import services.MainWindowService;
import signalUtils.SignalOperationType;

public class MainWindowController implements ServiceBindable<MainWindowService> {
    public Button ShowChartButton;
    public Button ShowHistogramButton;

    public Button ShowParametersButton;
    public Button AdditionOperationButton;
    public Button MultiplicationOperationButton;
    public Button SubtractionOperationButton;
    public Button DivisionOperationButton;
    public TableView SignalsTableView;
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
        windowService.openRemoveSignalDialog();
    }


    public void onAdditionClicked(ActionEvent actionEvent) {
        windowService.openSignalOperationDialog(SignalOperationType.ADDITION);
    }

    public void onMultiplicationClicked(ActionEvent actionEvent) {
        windowService.openSignalOperationDialog(SignalOperationType.MULTIPLICATION);

    }

    public void onSubtractionClicked(ActionEvent actionEvent) {
        windowService.openSignalOperationDialog(SignalOperationType.SUBTRACTION);
    }

    public void onDivisionClicked(ActionEvent actionEvent) {
        windowService.openSignalOperationDialog(SignalOperationType.DIVISION);
    }

    @Override
    public void setService(MainWindowService service) {
        if (service == null) {
            throw new IncorrectServiceControllerBindingException();
        }

        windowService = service;
    }
}

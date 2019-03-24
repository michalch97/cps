package controllers;

import exceptions.IncorrectServiceControllerBindingException;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import services.MainWindowService;

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

    }

    public void onShowHistogramClicked(ActionEvent actionEvent) {
    }

    public void onShowParametersClicked(ActionEvent actionEvent) {

    }

    public void onSaveSignalClicked(ActionEvent actionEvent) {

    }
    

    public void onLoadSignalClicked(ActionEvent actionEvent) {
    }

    public void onLoadTextSignalClicked(ActionEvent actionEvent) {

    }

    public void onAddNewSignalClicked(ActionEvent actionEvent) {
        windowService.openAddNewSignalDialog();
    }

    public void onRemoveSignalClicked(ActionEvent actionEvent) {

    }


    public void onAdditionClicked(ActionEvent actionEvent) {
    }

    public void onMultiplicationClicked(ActionEvent actionEvent) {
    }

    public void onSubtractionClicked(ActionEvent actionEvent) {
    }

    public void onDivisionClicked(ActionEvent actionEvent) {

    }

    @Override
    public void setService(MainWindowService service) {
        if (service == null) {
            throw new IncorrectServiceControllerBindingException();
        }

        windowService = service;
    }
}

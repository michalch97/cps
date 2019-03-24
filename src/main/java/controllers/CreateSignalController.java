package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import services.CreateSignalService;

public class CreateSignalController implements ServiceBindable<CreateSignalService> {
    public ComboBox SignalTypeComboBox;
    public TextField AmplitudeField;
    public TextField StartTimeField;
    public TextField DurationField;
    public TextField BasePeriodField;
    public TextField FillCoefficiencyField;
    public Button CreateButton;
    public Button CancelButton;

    @Override
    public void setService(CreateSignalService service) {

    }

    public void onCreateButtonClicked(ActionEvent actionEvent) {
    }

    public void onCancelButtonClicked(ActionEvent actionEvent) {
    }
}

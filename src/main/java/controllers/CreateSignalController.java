package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.DoubleSpinnerValueFactory;
import javafx.scene.control.TextField;
import services.CreateSignalService;
import signalUtils.SignalParameters;
import signalUtils.SignalType;

public class CreateSignalController extends Controller implements ServiceBindable<CreateSignalService> {
    public TextField SignalNameField;
    public ComboBox<SignalType> SignalTypeComboBox;
    public Spinner<Double> AmplitudeSpinner;
    public Spinner<Double> StartTimeSpinner;
    public Spinner<Double> JumpSpinner;
    public Spinner<Double> DurationSpinner;
    public Spinner<Double> BasePeriodSpinner;
    public Spinner<Double> FillCoefficiencySpinner;
    public Button CreateButton;
    public Button CancelButton;

    private CreateSignalService createSignalService;

    @Override
    public void setService(CreateSignalService service) {
        createSignalService = service;

        initSpinners();
        initComboBox();
    }

    public void onCreateButtonClicked(ActionEvent actionEvent) {
        SignalParameters signalParameters = SignalParameters.builder()
                                                            .amplitude(AmplitudeSpinner.getValue())
                                                            .startTime(StartTimeSpinner.getValue())
                                                            .jumpTime(JumpSpinner.getValue())
                                                            .duration(DurationSpinner.getValue())
                                                            .period(BasePeriodSpinner.getValue())
                                                            .fillFactor(FillCoefficiencySpinner.getValue())
                                                            .build();

        createSignalService.createSignal(SignalNameField.getText(), SignalTypeComboBox.getValue(), signalParameters);

        closeWindow(CreateButton); // TODO validation, exceptions handling, messages to user
    }

    public void onCancelButtonClicked(ActionEvent actionEvent) {
        closeWindow(CancelButton);
    }

    private void initSpinners() {
        AmplitudeSpinner.setValueFactory(new DoubleSpinnerValueFactory(0.d, Double.MAX_VALUE, 0.0d, 0.1d));
        StartTimeSpinner.setValueFactory(new DoubleSpinnerValueFactory(-Double.MAX_VALUE, Double.MAX_VALUE, 0.0d, 0.1d));
        JumpSpinner.setValueFactory(new DoubleSpinnerValueFactory(-Double.MAX_VALUE, Double.MAX_VALUE, 0.0d, 0.1d));
        DurationSpinner.setValueFactory(new DoubleSpinnerValueFactory(0.d, Double.MAX_VALUE, 0.0d, 0.1d));
        BasePeriodSpinner.setValueFactory(new DoubleSpinnerValueFactory(0.d, Double.MAX_VALUE, 0.0d, 0.1d));
        FillCoefficiencySpinner.setValueFactory(new DoubleSpinnerValueFactory(0.d, Double.MAX_VALUE, 0.0d, 0.1d));

        AmplitudeSpinner.setEditable(true);
        StartTimeSpinner.setEditable(true);
        JumpSpinner.setEditable(true);
        DurationSpinner.setEditable(true);
        BasePeriodSpinner.setEditable(true);
        FillCoefficiencySpinner.setEditable(true);
    }

    private void initComboBox() {
        ObservableList<SignalType> signalTypes = FXCollections.observableArrayList(SignalType.values());
        signalTypes.remove(SignalType.FIXED_SIGNAL);
        SignalTypeComboBox.setItems(signalTypes);

        CreateButton.disableProperty().bind(SignalTypeComboBox.valueProperty().isNull()
                                                              .or(SignalNameField.textProperty().isEmpty()));
    }
}

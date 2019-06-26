package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import services.SOIService;
import signalUtils.SignalType;
import signalUtils.WindowType;

public class SOIDialogController extends Controller implements ServiceBindable<SOIService> {
    public TextField SignalNameField;
    public Spinner<Integer> MSpinner;
    public Spinner<Integer> KSpinner;
    public ComboBox<WindowType> WindowTypeComboBox;
    public Button CreateButton;
    public Button CancelButton;
    private SOIService soiService;

    public void onCreateButtonClicked(ActionEvent event) {
        Double MValue = MSpinner.getValue().doubleValue();
        Double KValue = KSpinner.getValue().doubleValue();
        String signalName = SignalNameField.getText();

        soiService.createSignalByFiltration(signalName, MValue, KValue, WindowTypeComboBox.getValue());
        closeWindow(CreateButton);
    }

    public void onCancelButtonClicked(ActionEvent event) {
        closeWindow(CancelButton);
    }

    @Override
    public void setService(SOIService service) {
        this.soiService = service;

        initSpinners();
        initComboBox();
        CreateButton.disableProperty().bind(SignalNameField.textProperty().isEmpty());
    }

    private void initSpinners() {
        MSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2, Integer.MAX_VALUE, 2, 1));
        KSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2, Integer.MAX_VALUE, 2, 1));
        MSpinner.setEditable(true);
        KSpinner.setEditable(true);
    }

    private void initComboBox() {
        ObservableList<WindowType> windowTypes = FXCollections.observableArrayList(WindowType.values());
        WindowTypeComboBox.setItems(windowTypes);

        CreateButton.disableProperty().bind(WindowTypeComboBox.valueProperty().isNull()
                .or(SignalNameField.textProperty().isEmpty()));
    }
}

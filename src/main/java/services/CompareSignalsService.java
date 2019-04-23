package services;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import signalGenerators.Point;
import signalGenerators.SignalGenerator;
import signalUtils.MeanSquaredError;
import signalUtils.SignalParameters;
import signals.Signal;
import viewItems.SignalView;

@Getter
@Slf4j
public class CompareSignalsService extends Service {
    private static final String fxmlCreateSignalFileName = "fxml/signalComparisionDialog.fxml";

    private MainWindowService mainWindowService;
    private final SignalView signalView;
    Stage dialog;

    CompareSignalsService(MainWindowService mainWindowService, SignalView signalView) {
        this.mainWindowService = mainWindowService;
        this.signalView = signalView;

        dialog = new Stage();
        dialog.initOwner(mainWindowService.getStage());
        createScene(dialog, fxmlCreateSignalFileName);
    }

    public List<SignalView> getSignalsToChooseFrom() {
        return mainWindowService.getSignals()
                                .stream()
                                .filter(signal -> !Objects.equals(signalView, signal))
                                .collect(Collectors.toList());
    }

    public void compareSignals(SignalView otherView) {
        List<Point> mainPoints = generateDiscretePoint(signalView, 0.01d);
        List<Point> otherPoints = generateDiscretePoint(otherView, 0.01d);

        Double error = MeanSquaredError.getError(mainPoints, otherPoints);
        showErrorValue(error);
    }

    private void showErrorValue(Double errorValue) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Wartość błędu");
        alert.setContentText("Błąd: " + errorValue);
        alert.showAndWait();
    }

    private List<Point> generateDiscretePoint(SignalView view, Double timeStep) {
        Signal signal = view.getSignal();
        SignalParameters signalParameters = view.getSignalParameters();
        Double startTime = signalParameters.getStartTime();

        SignalGenerator signalGenerator = new SignalGenerator(signal, startTime, startTime + signalParameters.getDuration(), timeStep);

        return signalGenerator.generateSignal();
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Porównywanie sygnałów");
        stage.show();
    }
}

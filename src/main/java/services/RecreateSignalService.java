package services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import signalGenerators.Point;
import signalUtils.SignalParameters;
import signalUtils.SignalType;
import signals.DiscreteSignal;
import signals.RecreatedSignal;
import signals.Signal;
import viewItems.SignalView;

@Getter
@Slf4j
public class RecreateSignalService extends Service {
    private static final String fxmlCreateSignalFileName = "fxml/signalRecreationDialog.fxml";

    private MainWindowService mainWindowService;
    private final SignalView signalView;
    Stage dialog;

    RecreateSignalService(MainWindowService mainWindowService, SignalView signalView) {
        this.mainWindowService = mainWindowService;
        this.signalView = signalView;

        dialog = new Stage();
        dialog.initOwner(mainWindowService.getStage());
        createScene(dialog, fxmlCreateSignalFileName);
    }

    public void recreateSignal(String signalName) {
        Signal signal = signalView.getSignal();
        SignalParameters signalParameters = signalView.getSignalParameters();
        if (signal instanceof DiscreteSignal) {
            Double timeStep = ((DiscreteSignal) signal).getTimeStep();
            Collection<Double> xPoints = ((DiscreteSignal) signal).getXPoints();
            List<Point> points = xPoints.stream().map(xPoint -> new Point(xPoint, signal.calculateValue(xPoint))).collect(Collectors.toList());

            RecreatedSignal recreatedSignal = new RecreatedSignal(points, signalParameters.getStartTime(), timeStep, signalParameters.getAmplitude());
            SignalView view = new SignalView(signalName, recreatedSignal, signalParameters, SignalType.FIXED_SIGNAL);
            mainWindowService.addSignal(view);
        }
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Odtworzenie sygnału");
        stage.show();
    }
}

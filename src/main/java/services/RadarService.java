package services;

import java.util.List;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import radarSimulator.RadarSimulator;
import radarSimulator.SimulatorSettingsDto;
import signalGenerators.Point;
import signalUtils.SignalType;
import signals.FixedSignal;
import viewItems.SignalView;

import static signalUtils.SignalFactory.calculateNewSignalParameters;

public class RadarService extends Service {
    private static final String fxmlFileName = "fxml/radarWindow.fxml";
    private final MainWindowService mainWindowService;
    private final Stage dialog;

    RadarService(MainWindowService mainWindowService) {
        this.mainWindowService = mainWindowService;

        dialog = new Stage();
        dialog.initOwner(mainWindowService.getStage());
        createScene(dialog, fxmlFileName);
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Ustawienia czujnika odległości");
        stage.show();
    }

    public void createSimulation(SimulatorSettingsDto settingsDto, String resultName) {
        RadarSimulator radarSimulator = new RadarSimulator(settingsDto);

        List<Point> simulationResults = radarSimulator.simulate();
        List<Point> realResults = radarSimulator.getRealDistance();

        FixedSignal simulatedSignal = new FixedSignal(simulationResults, 0.d, 0.d);
        SignalView signalView = new SignalView(resultName, simulatedSignal, calculateNewSignalParameters(simulatedSignal), SignalType.FIXED_SIGNAL);

        FixedSignal realSignal = new FixedSignal(realResults, 0.d, 0.d);
        SignalView realSignalView = new SignalView(resultName+ " - Real", realSignal, calculateNewSignalParameters(realSignal), SignalType.FIXED_SIGNAL);

        mainWindowService.addSignal(signalView);
        mainWindowService.addSignal(realSignalView);
    }
}

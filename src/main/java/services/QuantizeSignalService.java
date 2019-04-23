package services;

import java.util.List;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import signalGenerators.Point;
import signalUtils.SignalQuantization;
import signalUtils.SignalType;
import signals.QuantiziedSignal;
import signals.Signal;
import viewItems.SignalView;

@Getter
@Slf4j
public class QuantizeSignalService extends Service {
    private static final String fxmlCreateSignalFileName = "fxml/quantizeWindow.fxml";

    private MainWindowService mainWindowService;
    private final SignalView signalView;
    Stage dialog;

    QuantizeSignalService(MainWindowService mainWindowService, SignalView signalView) {
        this.mainWindowService = mainWindowService;
        this.signalView = signalView;

        dialog = new Stage();
        dialog.initOwner(mainWindowService.getStage());
        createScene(dialog, fxmlCreateSignalFileName);
    }

    public void createQuantizedSignal(String signalName, Double timeStep, Integer quantumStepCount) {
        Signal signal = signalView.getSignal();
        List<Point> points = SignalQuantization.quantizeSignal(signal, signalView.getSignalParameters(), timeStep, quantumStepCount);

        QuantiziedSignal quantiziedSignal = new QuantiziedSignal(points, signal.getMaxAmplitude(), timeStep);
        SignalView view = new SignalView(signalName, quantiziedSignal, this.signalView.getSignalParameters(), SignalType.FIXED_SIGNAL);

        mainWindowService.addSignal(view);
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Kwantyzacja sygnału");
        stage.show();
    }
}

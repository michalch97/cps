package services;

import java.util.List;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import signalGenerators.Point;
import signalGenerators.SignalGenerator;
import signalUtils.SignalParameters;
import signalUtils.SignalType;
import signals.FixedSignal;
import signals.Signal;
import viewItems.SignalView;

@Getter
@Slf4j
public class SampleSignalService extends Service {
    private static final String fxmlCreateSignalFileName = "fxml/sampleWindow.fxml";

    private MainWindowService mainWindowService;
    private final SignalView signalView;
    Stage dialog;

    SampleSignalService(MainWindowService mainWindowService, SignalView signalView) {
        this.mainWindowService = mainWindowService;
        this.signalView = signalView;

        dialog = new Stage();
        dialog.initOwner(mainWindowService.getStage());
        createScene(dialog, fxmlCreateSignalFileName);
    }

    public void createSampledSignal(String name, Double timeStep) {
        Signal signal = signalView.getSignal();

        List<Point> points = generateDiscretePoint(timeStep);
        FixedSignal fixedSignal = new FixedSignal(points, signal.getMaxAmplitude(), timeStep);

        SignalView view = new SignalView(name, fixedSignal, signalView.getSignalParameters(), SignalType.FIXED_SIGNAL);
        mainWindowService.addSignal(view);
    }

    private List<Point> generateDiscretePoint(Double timeStep) {
        Signal signal = signalView.getSignal();
        SignalParameters signalParameters = signalView.getSignalParameters();
        Double startTime = signalParameters.getStartTime();

        SignalGenerator signalGenerator = new SignalGenerator(signal, startTime, startTime + signalParameters.getDuration(), timeStep);

        return signalGenerator.generateSignal();
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Próbkowanie sygnału");
        stage.show();
    }
}

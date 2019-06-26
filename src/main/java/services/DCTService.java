package services;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import signalGenerators.ComplexPoint;
import signalGenerators.Point;
import signalGenerators.SignalDCT;
import signalGenerators.SignalGenerator;
import signalUtils.SignalParameters;
import signalUtils.SignalType;
import signals.DiscreteSignal;
import signals.FixedSignal;
import signals.Signal;
import viewItems.SignalView;

import java.util.List;

import static signalUtils.SignalFactory.calculateNewSignalParameters;

public class DCTService extends Service {
    private static final String fxmlDCTFileName = "fxml/signalDCTDialog.fxml";
    private MainWindowService mainWindowService;
    private final SignalView signalView;
    private Stage dialog;
    public DCTService(MainWindowService mainWindowService, SignalView signalView) {
        this.mainWindowService = mainWindowService;
        this.signalView = signalView;
        dialog = new Stage();
        dialog.initOwner(mainWindowService.getStage());
        createScene(dialog, fxmlDCTFileName);
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Transformacja kosinusowa");
        stage.show();
    }

    public void createSignalByDCT(String signalName) {
        Signal signal = signalView.getSignal();
        SignalParameters signalParameters = signalView.getSignalParameters();
        Double timeStep = 0.01d;

        if (signal instanceof DiscreteSignal) {
            timeStep = ((DiscreteSignal) signal).getTimeStep();
        }

        SignalGenerator signalGenerator = new SignalGenerator(signal, signalParameters.getStartTime(), signalParameters.getDuration(), timeStep);
        List<Point> points = signalGenerator.generateSignal();

        SignalDCT signalDCT = new SignalDCT();
        long[] execution = new long[2];
        List<Point> resultPoints = signalDCT.transform(points,execution);
        long time = execution[1] - execution[0];

        FixedSignal fixedSignal = new FixedSignal(resultPoints, 0.d, timeStep);
        SignalView signalView = new SignalView(signalName, fixedSignal, calculateNewSignalParameters(fixedSignal), SignalType.FIXED_SIGNAL);

        mainWindowService.addSignal(signalView);
        mainWindowService.showTime(time);
    }
}

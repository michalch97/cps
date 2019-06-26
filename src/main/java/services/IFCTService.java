package services;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import signalGenerators.Point;
import signalGenerators.SignalFCT;
import signalGenerators.SignalGenerator;
import signalGenerators.SignalIFCT;
import signalUtils.SignalParameters;
import signalUtils.SignalType;
import signals.DiscreteSignal;
import signals.FixedSignal;
import signals.Signal;
import viewItems.SignalView;

import java.util.List;

import static signalUtils.SignalFactory.calculateNewSignalParameters;

public class IFCTService extends Service {
    private static final String fxmlIFCTFileName = "fxml/signalIFCTDialog.fxml";
    private MainWindowService mainWindowService;
    private final SignalView signalView;
    private Stage dialog;
    public IFCTService(MainWindowService mainWindowService, SignalView signalView) {
        this.mainWindowService = mainWindowService;
        this.signalView = signalView;
        dialog = new Stage();
        dialog.initOwner(mainWindowService.getStage());
        createScene(dialog, fxmlIFCTFileName);
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Szybka transformacja kosinusowa - przekształcenie odwrotne");
        stage.show();
    }

    public void createSignalByIFCT(String signalName) {
        Signal signal = signalView.getSignal();
        SignalParameters signalParameters = signalView.getSignalParameters();
        Double timeStep = 0.01d;

        if (signal instanceof DiscreteSignal) {
            timeStep = ((DiscreteSignal) signal).getTimeStep();
        }

        SignalGenerator signalGenerator = new SignalGenerator(signal, signalParameters.getStartTime(), signalParameters.getDuration(), timeStep);
        List<Point> points = signalGenerator.generateSignal();

        SignalIFCT signalIFCT = new SignalIFCT();
        long[] execution = new long[2];
        List<Point> resultPoints = signalIFCT.inverse(points,execution);
        long time = execution[1] - execution[0];

        FixedSignal fixedSignal = new FixedSignal(resultPoints, 0.d, timeStep);
        SignalView signalView = new SignalView(signalName, fixedSignal, calculateNewSignalParameters(fixedSignal), SignalType.FIXED_SIGNAL);

        mainWindowService.addSignal(signalView);
        mainWindowService.showTime(time);
    }
}

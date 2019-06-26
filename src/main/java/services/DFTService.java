package services;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import signalGenerators.ComplexPoint;
import signalGenerators.Point;
import signalGenerators.SignalDFT;
import signalGenerators.SignalGenerator;
import signalUtils.SignalParameters;
import signalUtils.SignalType;
import signals.DiscreteSignal;
import signals.FixedSignal;
import signals.Signal;
import viewItems.SignalView;

import java.util.Collection;
import java.util.List;

public class DFTService extends Service {
    private static final String fxmlDFTFileName = "fxml/signalDFTDialog.fxml";
    private MainWindowService mainWindowService;
    private final SignalView signalView;
    private Stage dialog;

    public DFTService(MainWindowService mainWindowService, SignalView signalView) {
        this.mainWindowService = mainWindowService;
        this.signalView = signalView;
        dialog = new Stage();
        dialog.initOwner(mainWindowService.getStage());
        createScene(dialog, fxmlDFTFileName);
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Dyskretna transformacja Fouriera");
        stage.show();
    }

    public void createSignalByDFT(String signalName) {
        Signal signal = signalView.getSignal();
        SignalParameters signalParameters = signalView.getSignalParameters();
        Double timeStep = 0.01d;

        if (signal instanceof DiscreteSignal) {
            timeStep = ((DiscreteSignal) signal).getTimeStep();
        }

        SignalGenerator signalGenerator = new SignalGenerator(signal, signalParameters.getStartTime(), signalParameters.getDuration(), timeStep);
        List<Point> points = signalGenerator.generateSignal();

        SignalDFT signalDFT = new SignalDFT();
        long[] execution = new long[2];
        List<ComplexPoint> resultPoints = signalDFT.transform(points,execution);
        long time = execution[1] - execution[0];

        FixedSignal fixedSignal = new FixedSignal(0.d, resultPoints, timeStep);
        SignalView signalView = new SignalView(signalName, fixedSignal, calculateNewSignalParameters(fixedSignal), SignalType.FIXED_SIGNAL);

        mainWindowService.addSignal(signalView);
        mainWindowService.showTime(time);
    }

    private SignalParameters calculateNewSignalParameters(FixedSignal signal) {
        SignalParameters.SignalParametersBuilder parametersBuilder = SignalParameters.builder();

        parametersBuilder.startTime(0.d).duration(0.d).amplitude(0.d).period(0.d).fillFactor(0.d);
        Collection<ComplexPoint> points = signal.getComplexPoints();
        if (points.size() > 0) {
            Double startTime = points.stream().map(ComplexPoint::getX).min(Double::compareTo).get();
            Double duration = points.stream().map(ComplexPoint::getX).max(Double::compareTo).get() - startTime;
            Double amplitude = points.stream().map(ComplexPoint::getYReal).map(Math::abs).max(Double::compareTo).get();
            parametersBuilder.startTime(startTime).duration(duration).amplitude(amplitude);
        }

        return parametersBuilder.build();
    }
}

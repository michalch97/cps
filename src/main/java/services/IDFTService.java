package services;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import signalGenerators.ComplexPoint;
import signalGenerators.Point;
import signalUtils.SignalParameters;
import signalUtils.SignalType;
import signals.DiscreteSignal;
import signals.FixedSignal;
import signalGenerators.SignalIDFT;
import viewItems.SignalView;

import java.util.Collection;
import java.util.List;

public class IDFTService extends Service {
    private static final String fxmlIDFTFileName = "fxml/signalIDFTDialog.fxml";
    private MainWindowService mainWindowService;
    private final SignalView signalView;
    private Stage dialog;

    public IDFTService(MainWindowService mainWindowService, SignalView signalView) {
        this.mainWindowService = mainWindowService;
        this.signalView = signalView;
        dialog = new Stage();
        dialog.initOwner(mainWindowService.getStage());
        createScene(dialog, fxmlIDFTFileName);
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Dyskretna transformacja Fouriera - przekształcenie odwrotne");
        stage.show();
    }

    public void createSignalByIDFT(String signalName) {
        FixedSignal signal = (FixedSignal) signalView.getSignal();
        SignalIDFT signalIDFT = new SignalIDFT();

        Double timeStep = ((DiscreteSignal) signal).getTimeStep();

        List<ComplexPoint> complexPoints = signal.getComplexPoints();
        long[] execution = new long[2];
        List<Point> resultPoints = signalIDFT.inverse(complexPoints,execution);
        long time = execution[1] - execution[0];

        FixedSignal fixedSignal = new FixedSignal(resultPoints, 0.d, timeStep);
        SignalView signalView = new SignalView(signalName, fixedSignal, calculateNewSignalParameters(fixedSignal), SignalType.FIXED_SIGNAL);

        mainWindowService.addSignal(signalView);
        mainWindowService.showTime(time);
    }

    private SignalParameters calculateNewSignalParameters(FixedSignal signal) {
        SignalParameters.SignalParametersBuilder parametersBuilder = SignalParameters.builder();

        parametersBuilder.startTime(0.d).duration(0.d).amplitude(0.d).period(0.d).fillFactor(0.d);
        Collection<Point> points = signal.getPoints();
        if (points.size() > 0) {
            Double startTime = points.stream().map(Point::getX).min(Double::compareTo).get();
            Double duration = points.stream().map(Point::getX).max(Double::compareTo).get() - startTime;
            Double amplitude = points.stream().map(Point::getY).map(Math::abs).max(Double::compareTo).get();
            parametersBuilder.startTime(startTime).duration(duration).amplitude(amplitude);
        }

        return parametersBuilder.build();
    }
}

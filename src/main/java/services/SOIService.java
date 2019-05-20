package services;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import signalGenerators.Point;
import signalGenerators.SignalGenerator;
import signalGenerators.SignalSOI;
import signalUtils.SignalParameters;
import signalUtils.SignalType;
import signalUtils.WindowType;
import signals.DiscreteSignal;
import signals.FixedSignal;
import signals.Signal;
import viewItems.SignalView;

import java.util.Collection;
import java.util.List;

public class SOIService extends Service {
    private static final String fxmlSOIFileName = "fxml/signalSOIDialog.fxml";
    private MainWindowService mainWindowService;
    private final SignalView signalView;
    private Stage dialog;

    public SOIService(MainWindowService mainWindowService, SignalView signalView) {
        this.mainWindowService = mainWindowService;
        this.signalView = signalView;
        dialog = new Stage();
        dialog.initOwner(mainWindowService.getStage());
        createScene(dialog, fxmlSOIFileName);
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Filtracja SOI");
        stage.show();
    }

    public void createSignalByFiltration(String newName, Double MValue, Double KValue, WindowType windowType) {
        Signal signal = signalView.getSignal();
        SignalParameters signalParameters = signalView.getSignalParameters();
        Double timeStep = 0.01d;

        if (signal instanceof DiscreteSignal) {
            timeStep = ((DiscreteSignal) signal).getTimeStep();
        }

        SignalGenerator signalGenerator = new SignalGenerator(signal, signalParameters.getStartTime(), signalParameters.getDuration(), timeStep);
        List<Point> points = signalGenerator.generateSignal();

        SignalSOI signalSOI = new SignalSOI();
        List<Point> resultPoints = signalSOI.filter(points,MValue,KValue,timeStep, windowType);

        FixedSignal fixedSignal = new FixedSignal(resultPoints, 0.d, timeStep);
        SignalView signalView = new SignalView(newName, fixedSignal, calculateNewSignalParameters(fixedSignal), SignalType.FIXED_SIGNAL);

        mainWindowService.addSignal(signalView);

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

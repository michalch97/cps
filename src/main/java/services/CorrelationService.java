package services;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import signalGenerators.Point;
import signalGenerators.SignalConvolution;
import signalGenerators.SignalCorrelation;
import signalGenerators.SignalGenerator;
import signalUtils.SignalParameters;
import signalUtils.SignalType;
import signals.DiscreteSignal;
import signals.FixedSignal;
import signals.Signal;
import viewItems.SignalView;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class CorrelationService extends Service{
    private static final String fxmlCorrelationFileName = "fxml/signalCorrelationDialog.fxml";
    private MainWindowService mainWindowService;
    private final SignalView signalView;
    private Stage dialog;

    public CorrelationService(MainWindowService mainWindowService, SignalView signalView) {
        this.mainWindowService = mainWindowService;
        this.signalView = signalView;
        dialog = new Stage();
        dialog.initOwner(mainWindowService.getStage());
        createScene(dialog, fxmlCorrelationFileName);
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Korelacja sygnałów");
        stage.show();
    }

    public List<SignalView> getSignalsToChooseFrom() {
        return mainWindowService.getSignals()
                .stream()
                .filter(signal -> !Objects.equals(signalView, signal))
                .collect(Collectors.toList());
    }

    public void createNewSignalByCorrelation(String newName, SignalView selectedSignal) {
        Signal firstSignal = signalView.getSignal();
        Signal secondSignal = selectedSignal.getSignal();

        SignalParameters firstSignalParameters = signalView.getSignalParameters();
        SignalParameters secondSignalParameters = selectedSignal.getSignalParameters();

        Double timeStep = 0.01d;
        if (firstSignal instanceof DiscreteSignal && secondSignal instanceof DiscreteSignal) {
            timeStep = ((DiscreteSignal) firstSignal).getTimeStep();
        }

        SignalGenerator firstSignalGenerator = new SignalGenerator(firstSignal, firstSignalParameters.getStartTime(), firstSignalParameters.getDuration(), timeStep);
        SignalGenerator secondSignalGenerator = new SignalGenerator(secondSignal, secondSignalParameters.getStartTime(), secondSignalParameters.getDuration(), timeStep);
        List<Point> firstPoints = firstSignalGenerator.generateSignal();
        List<Point> secondPoints = secondSignalGenerator.generateSignal();

        SignalCorrelation signalCorrelation = new SignalCorrelation();
        List<Point> resultPoints = signalCorrelation.correlation(firstPoints,secondPoints,timeStep);

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

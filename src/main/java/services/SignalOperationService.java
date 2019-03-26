package services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import exceptions.SignalOperationNotSupportedException;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import signalGenerators.Point;
import signalGenerators.SignalGenerator;
import signalGenerators.SignalOperations;
import signalUtils.SignalOperationType;
import signalUtils.SignalParameters;
import signalUtils.SignalParameters.SignalParametersBuilder;
import signalUtils.SignalType;
import signals.DiscreteSignal;
import signals.FixedSignal;
import signals.Signal;
import viewItems.SignalView;

@Getter
public class SignalOperationService extends Service {

    private static final String fxmlSignalOperationDialogFileName = "fxml/signalOperationDialog.fxml";
    private MainWindowService mainWindowService;
    private final SignalView signalView;
    private SignalOperationType type;

    public SignalOperationService(MainWindowService mainWindowService, SignalView signalView, SignalOperationType type) {
        this.mainWindowService = mainWindowService;
        this.signalView = signalView;
        this.type = type;

        Stage dialog = new Stage();
        dialog.initOwner(mainWindowService.getStage());
        createScene(dialog, fxmlSignalOperationDialogFileName);
    }

    public void createNewSignalByOperation(String newName, SignalView selectedSignal) {
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

        SignalOperations signalOperations = new SignalOperations();
        List<Point> resultPoints;
        switch (type) {
            case ADDITION:
                resultPoints = signalOperations.add(firstPoints, secondPoints, timeStep);
                break;
            case SUBTRACTION:
                resultPoints = signalOperations.subtract(firstPoints, secondPoints, timeStep);
                break;
            case MULTIPLICATION:
                resultPoints = signalOperations.multiply(firstPoints, secondPoints, timeStep);
                break;
            case DIVISION:
                resultPoints = signalOperations.divide(firstPoints, secondPoints, timeStep);
                break;
            default:
                throw new SignalOperationNotSupportedException();
        }

        FixedSignal fixedSignal = new FixedSignal(resultPoints, 0.d, timeStep);
        SignalView signalView = new SignalView(newName, fixedSignal, calculateNewSignalParameters(fixedSignal), SignalType.FIXED_SIGNAL);

        mainWindowService.addSignal(signalView);
    }

    public List<SignalView> getSignalsToChooseFrom() {
        return mainWindowService.getSignals()
                                .stream()
                                .filter(signal -> !Objects.equals(signalView, signal))
                                .collect(Collectors.toList());
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Wykonaj operację");
        stage.show();
    }

    private SignalParameters calculateNewSignalParameters(FixedSignal signal) {
        SignalParametersBuilder parametersBuilder = SignalParameters.builder();

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

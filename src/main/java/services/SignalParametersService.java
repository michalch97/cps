package services;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import signalUtils.SignalAttributes;
import signalUtils.SignalParameters;
import signals.Signal;
import viewItems.SignalView;

@Getter
public class SignalParametersService extends Service {

    private static final String fxmlSignalParametersFileName = "fxml/signalParametersDialog.fxml";
    private SignalView selectedItem;
    private SignalAttributes signalAttributes;

    public SignalParametersService(MainWindowService mainWindowService, SignalView selectedItem) {
        this.selectedItem = selectedItem;
        initParameters();

        Stage dialog = new Stage();
        dialog.initOwner(mainWindowService.getStage());
        createScene(dialog, fxmlSignalParametersFileName);
    }

    private void initParameters() {
        Signal signal = selectedItem.getSignal();
        SignalParameters signalParameters = selectedItem.getSignalParameters();
        Double startTime = signalParameters.getStartTime();
        Double endTime = signalParameters.getStartTime() + signalParameters.getDuration();

        Double average = signal.averageSignal(startTime, endTime);
        Double meanAverage = signal.meanAverageSignal(startTime, endTime);
        Double averagePower = signal.averageSignalPower(startTime, endTime);
        Double variation = signal.variationOfSignal(startTime, endTime);
        Double effectiveValue = signal.effectiveSignalValue(startTime, endTime);

        signalAttributes = SignalAttributes.builder()
                                           .average(average)
                                           .meanAverage(meanAverage)
                                           .averagePower(averagePower)
                                           .variation(variation)
                                           .effectiveValue(effectiveValue)
                                           .signalParameters(signalParameters)
                                           .build();
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Parametry " + selectedItem.getName());
        stage.show();
    }
}

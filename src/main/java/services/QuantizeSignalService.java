package services;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import signalUtils.SignalFactory;
import signalUtils.SignalParameters;
import signalUtils.SignalType;
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

    public void createSignal(String signalName, SignalType type, SignalParameters parameters) {
        Signal signal = SignalFactory.createSignal(parameters, type);
        SignalView signalView = new SignalView(signalName, signal, parameters, type);

        mainWindowService.addSignal(signalView);
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Kwantyzacja sygnału");
        stage.show();
    }
}

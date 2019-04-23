package services;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import viewItems.SignalView;

@Getter
@Slf4j
public class CompareSignalsService extends Service {
    private static final String fxmlCreateSignalFileName = "fxml/signalComparisionDialog.fxml";

    private MainWindowService mainWindowService;
    private final SignalView signalView;
    Stage dialog;

    CompareSignalsService(MainWindowService mainWindowService, SignalView signalView) {
        this.mainWindowService = mainWindowService;
        this.signalView = signalView;

        dialog = new Stage();
        dialog.initOwner(mainWindowService.getStage());
        createScene(dialog, fxmlCreateSignalFileName);
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
        stage.setTitle("Porównywanie sygnałów");
        stage.show();
    }
}

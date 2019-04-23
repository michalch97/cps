package services;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import viewItems.SignalView;

@Getter
@Slf4j
public class RecreateSignalService extends Service {
    private static final String fxmlCreateSignalFileName = "fxml/signalRecreationDialog.fxml";

    private MainWindowService mainWindowService;
    private final SignalView signalView;
    Stage dialog;

    RecreateSignalService(MainWindowService mainWindowService, SignalView signalView) {
        this.mainWindowService = mainWindowService;
        this.signalView = signalView;

        dialog = new Stage();
        dialog.initOwner(mainWindowService.getStage());
        createScene(dialog, fxmlCreateSignalFileName);
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Odtworzenie sygnału");
        stage.show();
    }
}

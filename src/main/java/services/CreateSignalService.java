package services;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class CreateSignalService extends Service {
    private static final String fxmlCreateSignalFileName = "fxml/createSignalDialog.fxml";

    private MainWindowService mainWindowService;
    Stage dialog;

    CreateSignalService(MainWindowService mainWindowService) {
        this.mainWindowService = mainWindowService;

        dialog = new Stage();
        dialog.initOwner(mainWindowService.getStage());
        createScene(dialog, fxmlCreateSignalFileName);
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Tworzenie sygnału");
        stage.show();
    }
}

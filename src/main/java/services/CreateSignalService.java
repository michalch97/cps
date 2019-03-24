package services;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class CreateSignalService extends Service {
    private static final String fxmlCreateSignalFileName = "fxml/createSignalDialog.fxml";

    private MainWindowService mainWindowService;

    CreateSignalService(MainWindowService mainWindowService) {
        this.mainWindowService = mainWindowService;

        Stage dialog = new Stage();
        createScene(dialog, fxmlCreateSignalFileName);
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle("Tworzenie sygnału");
        stage.show();
    }
}

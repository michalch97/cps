package services;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SaveDialogService extends Service {

    private static final String fxmlSaveDialogFileName = "fxml/saveDialog.fxml";

    public SaveDialogService() {
        Stage dialog = new Stage();
        createScene(dialog, fxmlSaveDialogFileName);
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.setTitle("Zapisywanie sygnału");
        stage.show();
    }
}

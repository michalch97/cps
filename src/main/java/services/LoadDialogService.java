package services;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoadDialogService extends Service {

    private static final String fxmlLoadDialogFileName = "fxml/loadDialog.fxml";

    public LoadDialogService() {
        Stage dialog = new Stage();
        createScene(dialog, fxmlLoadDialogFileName);
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.setTitle("Wczytaj z pliku");
        stage.show();
    }
}

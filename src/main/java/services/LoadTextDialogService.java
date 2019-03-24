package services;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoadTextDialogService extends LoadDialogService {

    private static final String fxmlLoadDialogFileName = "fxml/loadDialog.fxml";

    public LoadTextDialogService() {
        super();
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.setTitle("Wczytaj tekst z pliku");
        stage.show();
    }
}

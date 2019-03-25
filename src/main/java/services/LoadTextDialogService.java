package services;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoadTextDialogService extends LoadDialogService {

    private static final String fxmlLoadDialogFileName = "fxml/loadDialog.fxml";

    public LoadTextDialogService(MainWindowService mainWindowService) {
        super(mainWindowService);
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Wczytaj tekst z pliku");
        stage.show();
    }
}

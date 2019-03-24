package services;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoadDialogService extends Service {

    private static final String fxmlLoadDialogFileName = "fxml/loadDialog.fxml";

    public LoadDialogService(MainWindowService mainWindowService) {
        Stage dialog = new Stage();
        dialog.initOwner(mainWindowService.getStage());
        createScene(dialog, fxmlLoadDialogFileName);
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Wczytaj z pliku");
        stage.show();
    }
}

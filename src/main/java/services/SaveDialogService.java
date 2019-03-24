package services;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SaveDialogService extends Service {

    private static final String fxmlSaveDialogFileName = "fxml/saveDialog.fxml";

    public SaveDialogService(MainWindowService mainWindowService) {
        Stage dialog = new Stage();
        createScene(dialog, fxmlSaveDialogFileName);
        dialog.initOwner(mainWindowService.getStage());
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Zapisywanie sygnału");
        stage.show();
    }
}

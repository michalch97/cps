package services;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class RemoveSignalService extends Service {

    private static final String fxmlRemoveSignalFileName = "fxml/removeSignalConfirmationDialog.fxml";

    public RemoveSignalService() {
        Stage dialog = new Stage();
        createScene(dialog, fxmlRemoveSignalFileName);
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.setTitle("Usuwanie sygnału");
        stage.show();
    }
}

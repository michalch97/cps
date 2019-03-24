package services;

import javafx.scene.Scene;
import javafx.stage.Stage;
import signalUtils.SignalOperationType;

public class SignalOperationService extends Service {

    private static final String fxmlSignalOperationDialogFileName = "fxml/signalOperationDialog.fxml";

    public SignalOperationService(SignalOperationType type) {
        Stage dialog = new Stage();
        createScene(dialog, fxmlSignalOperationDialogFileName);
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.setTitle("Wykonaj operację");
        stage.show();
    }
}

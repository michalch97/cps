package services;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SignalParametersService extends Service {

    private static final String fxmlSignalParametersFileName = "fxml/signalParametersDialog.fxml";

    public SignalParametersService() {
        Stage dialog = new Stage();
        createScene(dialog, fxmlSignalParametersFileName);
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.setTitle("Parametry");
        stage.show();
    }
}

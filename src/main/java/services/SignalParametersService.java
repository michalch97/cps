package services;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SignalParametersService extends Service {

    private static final String fxmlSignalParametersFileName = "fxml/signalParametersDialog.fxml";

    public SignalParametersService(MainWindowService mainWindowService) {
        Stage dialog = new Stage();
        createScene(dialog, fxmlSignalParametersFileName);
        dialog.initOwner(mainWindowService.getStage());
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Parametry");
        stage.show();
    }
}

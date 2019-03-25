package services;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;

@Getter
public class SignalTextService extends Service {
    private static final String fxmlSignalTextFileName = "fxml/signalTextDialog.fxml";

    private MainWindowService mainWindowService;
    private String signalText;

    public SignalTextService(MainWindowService mainWindowService, String signalText) {
        this.mainWindowService = mainWindowService;
        this.signalText = signalText;

        Stage dialog = new Stage();
        dialog.initOwner(mainWindowService.getStage());
        createScene(dialog, fxmlSignalTextFileName);
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Sygnał z pliku w postaci tekstowej");
        stage.show();
    }
}

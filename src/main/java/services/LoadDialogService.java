package services;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import signalSerialization.SignalSerializator;
import viewItems.SignalView;

public class LoadDialogService extends Service {

    private static final String fxmlLoadDialogFileName = "fxml/loadDialog.fxml";

    private MainWindowService mainWindowService;

    public LoadDialogService(MainWindowService mainWindowService) {
        this.mainWindowService = mainWindowService;

        Stage dialog = new Stage();
        dialog.initOwner(mainWindowService.getStage());
        createScene(dialog, fxmlLoadDialogFileName);
    }

    public void loadSignalFromFile(String filePath) {
        SignalSerializator signalSerializator = new SignalSerializator();
        SignalView signalView = signalSerializator.deserializeSignal(filePath);

        mainWindowService.addSignal(signalView);
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Wczytaj z pliku");
        stage.show();
    }
}

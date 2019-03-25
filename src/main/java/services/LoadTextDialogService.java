package services;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import signalSerialization.SignalSerializator;

public class LoadTextDialogService extends LoadDialogService {

    private static final String fxmlLoadDialogFileName = "fxml/loadDialog.fxml";
    private MainWindowService mainWindowService;

    public LoadTextDialogService(MainWindowService mainWindowService) {
        super(mainWindowService);
        this.mainWindowService = mainWindowService;
    }

    @Override
    public void loadSignalFromFile(String filePath) {
        SignalSerializator signalSerializator = new SignalSerializator();
        String deserializedSignalString = signalSerializator.deserializeToString(filePath);

        new SignalTextService(mainWindowService, deserializedSignalString);
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Wczytaj tekst z pliku");
        stage.show();
    }
}

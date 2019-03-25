package services;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import signalSerialization.SignalSerializator;
import viewItems.SignalView;

@Getter
public class SaveDialogService extends Service {

    private static final String fxmlSaveDialogFileName = "fxml/saveDialog.fxml";
    private MainWindowService mainWindowService;
    private SignalView selectedItem;

    public SaveDialogService(MainWindowService mainWindowService, SignalView selectedItem) {
        this.mainWindowService = mainWindowService;
        this.selectedItem = selectedItem;

        Stage dialog = new Stage();
        dialog.initOwner(mainWindowService.getStage());
        createScene(dialog, fxmlSaveDialogFileName);
    }

    public void saveSignalToFile(String filePath) {
        SignalSerializator signalSerializator = new SignalSerializator();
        signalSerializator.serializeSignal(filePath, selectedItem);
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Zapisywanie sygnału");
        stage.show();
    }
}

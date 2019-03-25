package services;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import viewItems.SignalView;

@Getter
public class RemoveSignalService extends Service {

    private static final String fxmlRemoveSignalFileName = "fxml/removeSignalConfirmationDialog.fxml";

    private MainWindowService mainWindowService;
    private SignalView signalToRemove;

    public RemoveSignalService(MainWindowService mainWindowService, SignalView signalToRemove) {
        this.mainWindowService = mainWindowService;
        this.signalToRemove = signalToRemove;

        Stage dialog = new Stage();
        dialog.initOwner(mainWindowService.getStage());
        createScene(dialog, fxmlRemoveSignalFileName);
    }

    public void removeSignal() {
        mainWindowService.removeSignal(signalToRemove);
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Usuwanie sygnału");
        stage.show();
    }
}

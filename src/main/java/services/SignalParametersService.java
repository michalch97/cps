package services;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import viewItems.SignalView;

@Getter
public class SignalParametersService extends Service {

    private static final String fxmlSignalParametersFileName = "fxml/signalParametersDialog.fxml";
    private SignalView selectedItem;

    public SignalParametersService(MainWindowService mainWindowService, SignalView selectedItem) {
        this.selectedItem = selectedItem;

        Stage dialog = new Stage();
        dialog.initOwner(mainWindowService.getStage());
        createScene(dialog, fxmlSignalParametersFileName);
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Parametry " + selectedItem.getName());
        stage.show();
    }
}

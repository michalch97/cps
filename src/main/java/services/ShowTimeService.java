package services;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;

@Getter
public class ShowTimeService extends Service {
    private static final String fxmlShowTimeFileName = "fxml/showTimeDialog.fxml";
    private String time;
    public ShowTimeService(MainWindowService mainWindowService, long time) {
        this.time = Long.toString(time) + " ms";
        Stage dialog = new Stage();
        dialog.initOwner(mainWindowService.getStage());
        createScene(dialog, fxmlShowTimeFileName);
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Czas wykonania");
        stage.show();
    }
}

package services;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChartService extends Service {

    private static final String fxmlChartWindowFileName = "fxml/chartWindow.fxml";

    public ChartService() {
        Stage dialog = new Stage();
        createScene(dialog, fxmlChartWindowFileName);
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.setTitle("Wykres");
        stage.show();
    }
}

package services;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class HistogramService extends Service {

    private static final String fxmlHistogramWindowFileName = "fxml/histogramWindow.fxml";

    public HistogramService() {
        Stage dialog = new Stage();
        createScene(dialog, fxmlHistogramWindowFileName);
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.setTitle("Histogram");
        stage.show();
    }
}

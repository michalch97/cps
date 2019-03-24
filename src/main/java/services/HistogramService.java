package services;

import javafx.scene.Scene;
import javafx.stage.Stage;
import viewItems.SignalView;

public class HistogramService extends Service {

    private static final String fxmlHistogramWindowFileName = "fxml/histogramWindow.fxml";
    private SignalView selectedItem;

    public HistogramService(SignalView selectedItem) {
        this.selectedItem = selectedItem;

        Stage dialog = new Stage();
        createScene(dialog, fxmlHistogramWindowFileName);
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.setTitle("Histogram");
        stage.show();
    }
}

package services;

import javafx.scene.Scene;
import javafx.stage.Stage;
import viewItems.SignalView;

public class ChartService extends Service {

    private static final String fxmlChartWindowFileName = "fxml/chartWindow.fxml";
    private SignalView selectedItem;

    public ChartService(SignalView selectedItem) {
        this.selectedItem = selectedItem;

        Stage dialog = new Stage();
        createScene(dialog, fxmlChartWindowFileName);
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.setTitle("Wykres");
        stage.show();
    }
}

package services;

import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainWindowService extends Service {
    private static final String fxmlMainWindowFileName = "fxml/mainWindow.fxml";

    public MainWindowService(Stage primaryStage) {
        createScene(primaryStage, fxmlMainWindowFileName);
    }

    public void openAddNewSignalDialog() {
        new CreateSignalService(this);
    }

    void configureWindow(Stage stage, Scene scene) {
        stage.setTitle("Komputerowe przetwarzanie sygnałów");
        stage.setWidth(800);
        stage.setHeight(475);
        stage.setMinWidth(700);
        stage.setMinHeight(450);

        stage.show();
    }
}

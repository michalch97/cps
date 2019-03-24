package services;

import java.net.URL;
import java.util.Optional;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainWindowService extends Service {
    private static final String fxmlFileName = "fxml/mainWindow.fxml";

    public MainWindowService(Stage primaryStage) {
        URL resource = getFXMLResourceFileHandle(fxmlFileName);

        Optional<Parent> root = loadFXMLResource(resource);
        if (root.isPresent()) {
            primaryStage.setTitle("Komputerowe przetwarzanie sygnałów");

            Image applicationIcon = new Image("icons/app-icon.png");
            primaryStage.getIcons().add(applicationIcon);

            Scene scene = new Scene(root.get(), 800, 475);
            primaryStage.setScene(scene);
            primaryStage.setMinWidth(700);
            primaryStage.setMinHeight(450);
            primaryStage.show();
        }
    }
}

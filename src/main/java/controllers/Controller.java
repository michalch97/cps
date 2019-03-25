package controllers;

import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {
    protected void closeWindow(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
}

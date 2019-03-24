package services;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

import exceptions.FxmlNotFoundException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class Service {

    URL getFXMLResourceFileHandle(String fxmlFileName) {
        URL resource = getClass().getClassLoader().getResource(fxmlFileName);
        if (resource == null) {
            throw new FxmlNotFoundException(fxmlFileName);
        }

        return resource;
    }

    Optional<Parent> loadFXMLResource(URL resource) {
        try {
            Parent root = FXMLLoader.load(resource);
            return Optional.ofNullable(root);
        } catch (IOException e) {
            log.error("Loading file failed!", e);
            return Optional.empty();
        }
    }
}

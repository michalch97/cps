package services;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import signalUtils.SignalOperationType;
import viewItems.SignalView;

@Getter
public class SignalOperationService extends Service {

    private static final String fxmlSignalOperationDialogFileName = "fxml/signalOperationDialog.fxml";
    private MainWindowService mainWindowService;
    private final SignalView signalView;
    private SignalOperationType type;

    public SignalOperationService(MainWindowService mainWindowService, SignalView signalView, SignalOperationType type) {
        this.mainWindowService = mainWindowService;
        this.signalView = signalView;
        this.type = type;

        Stage dialog = new Stage();
        dialog.initOwner(mainWindowService.getStage());
        createScene(dialog, fxmlSignalOperationDialogFileName);
    }

    public List<SignalView> getSignalsToChooseFrom() {
        return mainWindowService.getSignals()
                                .stream()
                                .filter(signal -> !Objects.equals(signalView, signal))
                                .collect(Collectors.toList());
    }

    @Override
    void configureWindow(Stage stage, Scene scene) {
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Wykonaj operację");
        stage.show();
    }
}

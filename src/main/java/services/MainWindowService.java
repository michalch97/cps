package services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import signalUtils.SignalOperationType;
import viewItems.SignalView;

@Getter
@Slf4j
public class MainWindowService extends Service {
    private static final String fxmlMainWindowFileName = "fxml/mainWindow.fxml";

    private ObservableList<SignalView> signals;

    private Stage stage;

    public MainWindowService(Stage primaryStage) {
        signals = FXCollections.observableArrayList();
        stage = primaryStage;
        createScene(primaryStage, fxmlMainWindowFileName);
    }

    public void addSignal(SignalView signalView) {
        signals.add(signalView);
    }

    public void removeSignal(SignalView signalView) {
        signals.remove(signalView);
    }

    public void openAddNewSignalDialog() {
        new CreateSignalService(this);
    }

    public void openRemoveSignalDialog(SignalView signalView) {
        new RemoveSignalService(this, signalView);
    }

    public void openSaveDialog() {
        new SaveDialogService(this);
    }

    public void openLoadDialog() {
        new LoadDialogService(this);
    }

    public void openLoadTextDialog() {
        new LoadTextDialogService(this);
    }

    public void showChart() {
        new ChartService();
    }

    public void showHistogram() {
        new HistogramService();
    }

    public void showSignalParameters() {
        new SignalParametersService(this);
    }

    public void openSignalOperationDialog(SignalView signalView, SignalOperationType type) {
        new SignalOperationService(this, signalView, type);
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

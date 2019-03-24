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

    public void openAddNewSignalDialog() {
        new CreateSignalService(this);
    }

    public void openRemoveSignalDialog() {
        new RemoveSignalService();
    }

    public void openSaveDialog() {
        new SaveDialogService();
    }

    public void openLoadDialog() {
        new LoadDialogService();
    }

    public void openLoadTextDialog() {
        new LoadTextDialogService();
    }

    public void showChart() {
        new ChartService();
    }

    public void showHistogram() {
        new HistogramService();
    }

    public void showSignalParameters() {
        new SignalParametersService();
    }

    public void openSignalOperationDialog(SignalOperationType type) {
        new SignalOperationService(type);
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

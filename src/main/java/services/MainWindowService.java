package services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import signalUtils.SignalFactory;
import signalUtils.SignalOperationType;
import signalUtils.SignalParameters;
import signalUtils.SignalType;
import signals.Signal;
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

        createMockSignal(); // TODO remove when done testing
    }

    public void createMockSignal() {
        SignalParameters signalParameters = SignalParameters.builder()
                                                            .amplitude(10.d)
                                                            .period(1.337d)
                                                            .duration(4.20d)
                                                            .startTime(0.d)
                                                            .fillFactor(6.9d)
                                                            .build();

        Signal signal = SignalFactory.createSignal(signalParameters, SignalType.NOISE);
        SignalView signalView = new SignalView("Name ABC", signal, signalParameters, SignalType.NOISE);
        addSignal(signalView);
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

    public void showChart(SignalView selectedItem) {
        new ChartService(selectedItem);
    }

    public void showHistogram(SignalView selectedItem) {
        new HistogramService(selectedItem);
    }

    public void showSignalParameters(SignalView selectedItem) {
        new SignalParametersService(this, selectedItem);
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

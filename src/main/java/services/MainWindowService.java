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
                                                            .period(2.d)
                                                            .duration(6.d)
                                                            .startTime(0.d)
                                                            .fillFactor(0.d)
                                                            .build();

        Signal signal = SignalFactory.createSignal(signalParameters, SignalType.SIN);
        SignalView signalView = new SignalView("Sin 1", signal, signalParameters, SignalType.SIN);
        addSignal(signalView);
        signal = SignalFactory.createSignal(signalParameters, SignalType.SIN);
        signalView = new SignalView("Sin 2", signal, signalParameters, SignalType.SIN);
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

    public void openSaveDialog(SignalView selectedItem) {
        new SaveDialogService(this, selectedItem);
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

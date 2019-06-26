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

    public void showTime(long time) {new ShowTimeService(this,time);}

    public void addSignal(SignalView signalView) {
        signals.add(signalView);
    }

    public void removeSignal(SignalView signalView) {
        signals.remove(signalView);
    }

    public void sampleSignal(SignalView signalView) {
        new SampleSignalService(this, signalView);
    }

    public void quantizeSignal(SignalView signalView) {
        new QuantizeSignalService(this, signalView);
    }

    public void recreateSignal(SignalView signalView) {
        new RecreateSignalService(this, signalView);
    }

    public void compareSignal(SignalView signalView) {
        new CompareSignalsService(this, signalView);
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

    public void convolutionSignal(SignalView selectedItem){
         new ConvolutionService(this,selectedItem);
    }

    public void correlationSignal(SignalView selectedItem) {
        new CorrelationService(this,selectedItem);
    }

    public void SOISignal(SignalView selectedItem) {
        new SOIService(this,selectedItem);
    }

    public void DFTSignal(SignalView selectedItem){
        new DFTService(this, selectedItem);
    }

    public void IDFTSignal(SignalView selectedItem){
        new IDFTService(this, selectedItem);
    }

    void configureWindow(Stage stage, Scene scene) {
        stage.setTitle("Cyfrowe Przetwarzanie Sygnału");
        stage.setWidth(900);
        stage.setHeight(475);
        stage.setMinWidth(900);
        stage.setMinHeight(475);

        stage.show();
    }

    public void openRadarSimulation() {
        new RadarService(this);
    }

    public void DCTSignal(SignalView selectedItem) {
        new DCTService(this,selectedItem);
    }

    public void IDCTSignal(SignalView selectedItem) {
        new IDCTService(this,selectedItem);
    }

    public void FCTSignal(SignalView selectedItem) {
        new FCTService(this,selectedItem);
    }

    public void IFCTSignal(SignalView selectedItem) {
        new IFCTService(this,selectedItem);
    }

    public void FFTSignal(SignalView selectedItem) {
        new FFTService(this,selectedItem);
    }
}

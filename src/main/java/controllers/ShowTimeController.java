package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import services.ShowTimeService;

public class ShowTimeController extends Controller implements ServiceBindable<ShowTimeService> {
    public Button OKButton;
    public Label Time;
    public ShowTimeService service;
    @Override
    public void setService(ShowTimeService service) {
        this.service = service;
        this.Time.setText(service.getTime());
    }

    public void onOKButtonClicked(ActionEvent actionEvent) {
        closeWindow(OKButton);
    }
}

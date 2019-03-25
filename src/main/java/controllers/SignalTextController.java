package controllers;

import javafx.scene.control.TextArea;
import services.SignalTextService;

public class SignalTextController implements ServiceBindable<SignalTextService> {
    public TextArea SignalTextArea;
    private SignalTextService service;

    @Override
    public void setService(SignalTextService service) {
        this.service = service;

        SignalTextArea.setText(service.getSignalText());
    }
}

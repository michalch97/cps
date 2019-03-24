package controllers;

import javafx.scene.control.TextField;
import services.SignalParametersService;

public class SignalParametersController implements ServiceBindable<SignalParametersService> {
    public TextField AverageField;
    public TextField AverageMeanValueField;
    public TextField EffectiveValueField;
    public TextField VariationField;
    public TextField AveragePowerField;
    public TextField StartTimeField;
    public TextField DurationField;
    public TextField PeriodField;
    public TextField FillFactorField;

    private SignalParametersService signalParametersService;

    @Override
    public void setService(SignalParametersService service) {
        signalParametersService = service;
    }
}

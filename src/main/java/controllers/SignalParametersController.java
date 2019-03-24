package controllers;

import javafx.scene.control.TextField;
import services.SignalParametersService;
import signalUtils.SignalParameters;
import viewItems.SignalView;

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

        initFields();
        disableFieldEditing();
    }

    private void initFields() {
        // TODO calculate attributes for signal
        SignalView selectedItem = signalParametersService.getSelectedItem();
        SignalParameters signalParameters = selectedItem.getSignalParameters();

        StartTimeField.setText(String.valueOf(signalParameters.getStartTime()));
        DurationField.setText(String.valueOf(signalParameters.getDuration()));
        PeriodField.setText(String.valueOf(signalParameters.getPeriod()));
        FillFactorField.setText(String.valueOf(signalParameters.getFillFactor()));
    }

    private void disableFieldEditing() {
        AverageField.setEditable(false);
        AverageMeanValueField.setEditable(false);
        EffectiveValueField.setEditable(false);
        VariationField.setEditable(false);
        AveragePowerField.setEditable(false);
        StartTimeField.setEditable(false);
        DurationField.setEditable(false);
        PeriodField.setEditable(false);
        FillFactorField.setEditable(false);
    }
}

package controllers;

import java.text.DecimalFormat;

import javafx.scene.control.TextField;
import services.SignalParametersService;
import signalUtils.SignalAttributes;
import signalUtils.SignalParameters;

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
        SignalAttributes signalAttributes = signalParametersService.getSignalAttributes();
        SignalParameters signalParameters = signalAttributes.getSignalParameters();

        DecimalFormat df = new DecimalFormat("#0.0000");

        StartTimeField.setText(df.format(signalParameters.getStartTime()));
        DurationField.setText(df.format(signalParameters.getDuration()));
        PeriodField.setText(df.format(signalParameters.getPeriod()));
        FillFactorField.setText(df.format(signalParameters.getFillFactor()));
        AverageField.setText(df.format(signalAttributes.getAverage()));
        AverageMeanValueField.setText(df.format(signalAttributes.getMeanAverage()));
        EffectiveValueField.setText(df.format(signalAttributes.getEffectiveValue()));
        VariationField.setText(df.format(signalAttributes.getVariation()));
        AveragePowerField.setText(df.format(signalAttributes.getAveragePower()));
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

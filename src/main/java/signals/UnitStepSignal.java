package signals;

public class UnitStepSignal extends Signal {
    private Double startTime;

    public UnitStepSignal(Double amplitude, Double startTime){
        super(amplitude);
        this.startTime = startTime;
    }
    @Override
    public Double calculateValue(Double xPoint) {
        if(xPoint>startTime)
            return getMaxAmplitude();
        if(xPoint==startTime)
            return getMaxAmplitude()/2.d;
        return 0.d;
    }
}

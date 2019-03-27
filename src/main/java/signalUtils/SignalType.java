package signalUtils;

public enum SignalType {
    GAUSSIAN_NOISE,
    IMPULSE_NOISE,
    NOISE,
    RECTANGULAR,
    RECTANGULAR_SYMMETRICAL,
    SIN_FULL_WAVE,
    SIN_HALF_WAVE,
    SIN,
    TRIANGULAR,
    UNIT_IMPULSE,
    UNIT_STEP,
    FIXED_SIGNAL;

    @Override
    public String toString() {
        switch (this) {
            case GAUSSIAN_NOISE:
                return "Szum gaussowski";
            case IMPULSE_NOISE:
                return "Szum impulsowy";
            case NOISE:
                return "Szum o rozkładzie jednostajnym";
            case RECTANGULAR:
                return "Sygnał prostokątny";
            case RECTANGULAR_SYMMETRICAL:
                return "Sygnał prostokątny symetryczny";
            case SIN_FULL_WAVE:
                return "Sygnał sinusoidalny wyprostowany dwupołówkowo";
            case SIN_HALF_WAVE:
                return "Sygnał sinusoidalny wyprostowany jednopołówkowo";
            case SIN:
                return "Sygnał sinusoidalny";
            case TRIANGULAR:
                return "Sygnał trójkątny";
            case UNIT_IMPULSE:
                return "Impuls jednostkowy";
            case UNIT_STEP:
                return "Skok jednostkowy";
            default:
                throw new IllegalArgumentException();
        }
    }
}

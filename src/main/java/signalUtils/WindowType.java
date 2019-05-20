package signalUtils;

public enum WindowType {
    RECTANGULAR,
    HANNING;

    @Override
    public String toString() {
        switch (this) {
            case HANNING:
                return "Okno Hanninga";
            case RECTANGULAR:
                return "Okno prostokątne";
            default:
                throw new IllegalArgumentException();
        }
    }
}

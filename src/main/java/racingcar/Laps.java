package racingcar;

public class Laps {
    private int lap;
    public static final int MIN_LAP_COUNT = 0;

    public Laps() {
        lap = MIN_LAP_COUNT;
    }

    public Laps(int lap_count) {
        lap = lap_count;
    }

    public int getNumericLap() {
        return lap;
    }

    public void setLap(int lap_count) {
        lap = lap_count;
    }

    public void decrease() {
        if (lap > MIN_LAP_COUNT) {
            lap--;
        }
    }

    public boolean hasRemainingLaps() {
        if (lap > MIN_LAP_COUNT) {
            return true;
        }
        return false;
    }
}

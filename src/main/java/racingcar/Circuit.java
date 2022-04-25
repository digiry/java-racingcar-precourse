package racingcar;

public class Circuit {
    private RacingCarEntries entries;
    private Laps laps;

    public Circuit() {
        entries = new RacingCarEntries();
        laps = new Laps();
    }

    public void registerCarEntries(CommaSeparatedCarNames car_names) {
        for (CarName name : car_names) {
            entries.addNewRacingCar(name);
        }
    }

    public void registerLapsCount(Laps lap_count) {
        laps.setLaps(lap_count);
    }

    public int getLengthOfCarEntries() {
        return entries.size();
    }

    public RacingCar getRacingCarAt(int index) {
        return entries.get(index);
    }

    public boolean isFinished() {
        if (laps.hasRemainingLaps() == false) {
            return true;
        }

        return false;
    }
}

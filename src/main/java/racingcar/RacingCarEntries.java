package racingcar;

import java.util.ArrayList;
import java.util.Iterator;

public class RacingCarEntries implements Iterable<RacingCar> {
    private ArrayList<RacingCar> entries;

    public RacingCarEntries() {
        entries = new ArrayList<>();
    }

    public RacingCarEntries(ArrayList<RacingCar> car_entries) {
        entries = car_entries;
    }

    public void addNewRacingCar(CarName car_name) {
        entries.add(new RacingCar(new CarName(car_name.toString())));
    }

    public RacingCar get(int index) {
        return entries.get(index);
    }

    public void takeALap() {
        for (RacingCar car : entries) {
            car.moveOrStop();
        }
    }

    @Override
    public Iterator<RacingCar> iterator() {
        return new RacingCarIterator();
    }

    class RacingCarIterator implements Iterator<RacingCar> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < entries.size();
        }

        @Override
        public RacingCar next() {
            RacingCar current = entries.get(index);
            index++;

            return current;
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }
    }
}

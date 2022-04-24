package racingcar;

import java.util.ArrayList;
import java.util.Iterator;

public class CommaSeparatedCarNames implements Iterable<CarName> {
    private ArrayList<CarName> names;

    public CommaSeparatedCarNames(String car_names) {
        names = new ArrayList<>();

        for (String name : car_names.split(",")) {
            names.add(new CarName(name));
        }
    }

    public CarName get(int index) {
        return names.get(index);
    }

    public int size() {
        return names.size();
    }

    @Override
    public Iterator<CarName> iterator() {
        return new CarNamesIterator();
    }

    class CarNamesIterator implements Iterator<CarName> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < names.size();
        }

        @Override
        public CarName next() {
            CarName current = names.get(index);
            index++;

            return current;
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }
    }
}

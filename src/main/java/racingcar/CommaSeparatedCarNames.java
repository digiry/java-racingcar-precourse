package racingcar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class CommaSeparatedCarNames implements Iterable<String> {
    private ArrayList<String> names;

    public CommaSeparatedCarNames(String car_names) {
        names = new ArrayList(Arrays.asList(car_names.split(",")));
    }

    public String get(int index) {
        return names.get(index);
    }

    public int size() {
        return names.size();
    }

    @Override
    public Iterator<String> iterator() {
        return new CarNamesIterator();
    }

    class CarNamesIterator implements Iterator<String> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < names.size();
        }

        @Override
        public String next() {
            String current = names.get(index);
            index++;

            return current;
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }
    }
}

package racingcar;

import java.util.Collections;

public class Distance {
    private int distance;
    public static final int DEFAULT_DISTANCE = 0;

    public Distance() {
        distance = DEFAULT_DISTANCE;
    }

    public int getDistance() {
        return distance;
    }

    public void increase() {
        distance++;
    }

    public String toDashMarkup() {
        return String.join("", Collections.nCopies(distance, "-"));
    }
}

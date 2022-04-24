package racingcar;

public class RacingCar implements Comparable<RacingCar> {
    private final CarName name;
    private final Distance distance;

    public RacingCar(CarName car_name) {
        name = new CarName(car_name.toString());
        distance = new Distance();
    }

    public CarName getCarName() {
        return name;
    }

    public Distance getDistance() {
        return distance;
    }

    public void move() {
        distance.increase();
    }

    @Override
    public int compareTo(RacingCar other) {
        return distance.compareTo(other.getDistance());
    }
}

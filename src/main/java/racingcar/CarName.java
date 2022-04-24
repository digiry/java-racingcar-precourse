package racingcar;

public class CarName {
    private String name;

    public CarName(String car_name) {
        name = car_name;
    }

    @Override
    public String toString() {
        return name.toString();
    }
}

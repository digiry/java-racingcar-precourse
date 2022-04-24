package racingcar;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RacingCarTest {

    RacingCar makeRacingCarMovedAs(String car_name, int step) {
        RacingCar car = new RacingCar(new CarName(car_name));

        for (int i = 0; i < step; ++i) {
            car.move();
        }

        return car;
    }

    @ParameterizedTest
    @CsvSource(value = {"3:2:1", "1:4:-1", "2:2:0"}, delimiter = ':')
    @DisplayName("거리가 다른 경주 자동차과 비교할 수 있는지 검사한다")
    void compareBothRacingCars(int a_step, int b_step, int expected) {
        RacingCar a_car = makeRacingCarMovedAs("a_car", a_step);
        RacingCar b_car = makeRacingCarMovedAs("b_car", b_step);

        int result = a_car.compareTo(b_car);

        assertThat(result).isEqualTo(expected);
    }
}

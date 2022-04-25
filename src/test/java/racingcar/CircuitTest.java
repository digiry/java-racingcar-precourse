package racingcar;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CircuitTest {
    @Test
    @DisplayName("콤마로 구분한 참가 자동차 이름 목록으로 서킷에 자동차 등록이 되는지 검사한다")
    void registerRacingCarEntriesByCommaSeparatedCarNames() {
        CommaSeparatedCarNames car_names = new CommaSeparatedCarNames("a_car,b_car,c_car");
        Circuit test_circuit = new Circuit();

        test_circuit.registerCarEntries(car_names);

        for (int i = 0; i < test_circuit.getLengthOfCarEntries(); ++i) {
            RacingCar car = test_circuit.getRacingCarAt(i);

            assertThat(new String[] {"a_car", "b_car", "c_car"}).contains(car.getCarName().toString());
        }
    }

    @Test
    @DisplayName("잔여 Laps이 0일 때 경기 완료로 알 수 있는지 검사한다")
    void checkFinishedRaceIfRemainingLapsIsZero() {
        Laps zero_laps = new Laps(0);
        Circuit test_circuit = new Circuit();
        test_circuit.registerLapsCount(zero_laps);

        boolean finished = test_circuit.isFinished();

        assertThat(finished).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4})
    @DisplayName("잔여 Laps이 1 이상이면 경주 완료가 아님을 검사한다")
    void checkInProgressRaceIfRemainingLapsIsGreaterEqualThenZero(int lap_count) {
        Laps laps = new Laps(lap_count);
        Circuit test_circuit = new Circuit();
        test_circuit.registerLapsCount(laps);

        boolean finished = test_circuit.isFinished();

        assertThat(finished).isFalse();
    }
}

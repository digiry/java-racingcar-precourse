package racingcar;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LapsTest {
    @Test
    @DisplayName("Laps 생성 시 전달한 횟수가 보관되는지 검사한다")
    void getLapCountAfterCreation() {
        Laps laps = new Laps(5);

        assertThat(laps.getNumericLap()).isEqualTo(5);
    }

    @Test
    @DisplayName("Laps가 한 바퀴 감소하는지 검사한다")
    void decreaseLapsCount() {
        Laps laps = new Laps();

        laps.setLap(5);
        laps.decrease();

        assertThat(laps.getNumericLap()).isEqualTo(4);
    }

    @Test
    @DisplayName("Laps가 0이면 더이상 감소하지 않는지 검사한다")
    void keepMinLapCountWheneverDecreasing() {
        Laps laps = new Laps(1);

        laps.decrease();
        laps.decrease();

        assertThat(laps.getNumericLap()).isEqualTo(Laps.MIN_LAP_COUNT);
    }

    @Test
    @DisplayName("Laps에 남은 이동 횟수가 없는지 검사한다")
    void hasRemainingLapCount() {
        Laps laps = new Laps();
        laps.setLap(1);

        assertThat(laps.hasRemainingLaps()).isTrue();

        laps.decrease();

        assertThat(laps.hasRemainingLaps()).isFalse();
    }
}

package racingcar;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DistanceTest {
    @Test
    @DisplayName("생성할 때 기본 거리 0으로 설정하는지 검사한다")
    void checkZeroDistanceAsDefaultWhenCreation() {
        Distance dist = new Distance();

        assertThat(dist.getDistance()).isEqualTo(Distance.DEFAULT_DISTANCE);
    }

    Distance makeDistanceMovedAs(int step) {
        Distance dist = new Distance();

        for (int i = 0; i < step; ++i) {
            dist.increase();
        }

        return dist;
    }

    @Test
    @DisplayName("한 칸 이동으로 거리가 1 증가하는지 검사한다")
    void increaseDistanceOnce() {
        int one_step = 1;
        Distance dist = makeDistanceMovedAs(one_step);

        assertThat(dist.getDistance()).isEqualTo(one_step);
    }

    @ParameterizedTest
    @CsvSource(value = {"0:''", "1:-", "2:--", "3:---"}, delimiter = ':')
    @DisplayName("이동 거리를 대시(-) 표기 문자열로 반환을 검사한다")
    void convertDistanceAsDashMarkup(int steps, String dashSteps) {
        Distance dist = makeDistanceMovedAs(steps);

        assertThat(dist.toDashMarkup()).isEqualTo(dashSteps);
    }
}

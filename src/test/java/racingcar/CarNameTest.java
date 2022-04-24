package racingcar;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CarNameTest {
    @Test
    @DisplayName("생성할 때 전달한 자동차 이름을 출력 함수에서 사용할 때 이름을 출력하는지 검사한다")
    void checkStringOfCarNameOnceCarNameCreatedWithName() {
        String simple_name = "abc";
        CarName test_car = new CarName(simple_name);

        assertThat(test_car).hasToString(simple_name);
    }
}

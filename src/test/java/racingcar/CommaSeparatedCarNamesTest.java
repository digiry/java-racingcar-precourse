package racingcar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommaSeparatedCarNamesTest {
    @Test
    @DisplayName("콤마로 구분한 2개 이름 문자열을 입력받아 개별 이름으로 분리하는지 검사한다")
    void separateNamesWithCommaIntoEachNames() {
        String sample_names = "pobi,woni";

        CommaSeparatedCarNames csv_names = new CommaSeparatedCarNames(sample_names);

        assertThat(csv_names.size()).isEqualTo(2);
        assertThat(csv_names.get(0).toString()).isEqualTo("pobi");
        assertThat(csv_names.get(1).toString()).isEqualTo("woni");
    }

    @Test
    @DisplayName("1개 이름 문자열을 입력 받을 때 정상 생성하는지 검사한다")
    void createCommaSeparatedCarNamesWithSingleName() {
        String sample_name = "pobi";

        CommaSeparatedCarNames csv_names = new CommaSeparatedCarNames(sample_name);

        assertThat(csv_names.size()).isEqualTo(1);
        assertThat(csv_names.get(0).toString()).isEqualTo("pobi");
    }

    @Test
    @DisplayName("foreach 구문에서 동작하는지 검사한다")
    void checkIterableInForeachStatement() {
        String sample_names = "pobi,woni";

        CommaSeparatedCarNames csv_names = new CommaSeparatedCarNames(sample_names);

        for (CarName name : csv_names) {
            assertThat(new String[]{"pobi", "woni"}).contains(name.toString());
        }
    }

    @Test
    @DisplayName("이름 길이 규칙을 안 지킨 이름 문자열을 입력 받을 때 예외를 발생하는지 검사한다")
    void throwIllegalArgumentExceptionIfInputNameViolateNamingRule() {
        String sample_names = "pobi,overname";

        assertThatThrownBy(() -> {
            CommaSeparatedCarNames csv_names = new CommaSeparatedCarNames(sample_names);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}

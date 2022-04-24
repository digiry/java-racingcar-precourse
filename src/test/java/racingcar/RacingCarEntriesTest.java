package racingcar;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.RacingCar.RandomUtil;

public class RacingCarEntriesTest {
    class FakeRandomUtil implements RandomUtil {
        Integer testNumber;

        FakeRandomUtil(Integer number) {
            testNumber = number;
        }

        @Override
        public int getNumberInRange(int startInclusive, int endInclusive) {
            return testNumber;
        }
    }

    @Test
    @DisplayName("경주 자동차 3대 추가를 검사한다")
    void addTripleRacingCars() {
        RacingCarEntries entries = new RacingCarEntries();

        entries.addNewRacingCar(new CarName("a_car"));
        entries.addNewRacingCar(new CarName("b_car"));
        entries.addNewRacingCar(new CarName("c_car"));

        for (int i = 0; i < 3; ++i) {
            CarName name = entries.get(i).getCarName();
            assertThat(new String[]{"a_car", "b_car", "c_car"}).contains(name.toString());
        }
    }

    String parseCarName(String name_random) {
        String[] nameWithRandom = name_random.split(":");
        return nameWithRandom[0];
    }

    int parseRandomValue(String name_random) {
        String[] nameWithRandom = name_random.split(":");
        return Integer.parseInt(nameWithRandom[1]);
    }

    RacingCarEntries makeRacingCarArrayListWithRandomValue(String[] carName_random_list) {
        ArrayList<RacingCar> car_list = new ArrayList<>();

        for (String name_random : carName_random_list) {
            String name = parseCarName(name_random);
            int random = parseRandomValue(name_random);
            RandomUtil fakeRandomUtil = new FakeRandomUtil(random);
            RacingCar car = new RacingCar(new CarName(name), fakeRandomUtil);

            car_list.add(car);
        }

        return new RacingCarEntries(car_list);
    }

    @Test
    @DisplayName("랜덤값 4를 가진 자동차 3대가 모두 이동하는지 검사한다")
    void moveEveryRacingCarsWithRandomNumber4() {
        String[] sample_data = {"a_car:4", "b_car:4", "c_car:4"};
        int ONE_STEP_DISTANCE = 1;
        RacingCarEntries test_entries = makeRacingCarArrayListWithRandomValue(sample_data);

        test_entries.takeALap();

        for (RacingCar car : test_entries) {
            Distance dist = car.getDistance();
            assertThat(dist.getNumericDistance()).isEqualTo(ONE_STEP_DISTANCE);
        }
    }

    @Test
    @DisplayName("랜덤값 3을 가진 자동차 3대가 모두 정지했는지 검사한다")
    void stoppedEveryRacingCarsWithRandomNumber3() {
        String[] sample_data = {"a_car:3", "b_car:3", "c_car:3"};
        int ZERO_DISTANCE = 0;
        RacingCarEntries test_entries = makeRacingCarArrayListWithRandomValue(sample_data);

        test_entries.takeALap();

        for (RacingCar car : test_entries) {
            Distance dist = car.getDistance();
            assertThat(dist.getNumericDistance()).isEqualTo(ZERO_DISTANCE);
        }
    }

    RacingCar makeRacingCarMovedAs(String car_name, int step) {
        RacingCar car = new RacingCar(new CarName(car_name));

        for (int i = 0; i < step; ++i) {
            car.move();
        }

        return car;
    }

    int parseDistance(String name_dist) {
        String[] nameWithDist = name_dist.split(":");
        return Integer.parseInt(nameWithDist[1]);
    }

    RacingCarEntries makeRacingCarArrayListWithFixedDistance(String[] carName_dist_list) {
        ArrayList<RacingCar> car_list = new ArrayList<>();

        for (String name_dist : carName_dist_list) {
            String name = parseCarName(name_dist);
            int dist = parseDistance(name_dist);
            RacingCar car = makeRacingCarMovedAs(name, dist);

            car_list.add(car);
        }

        return new RacingCarEntries(car_list);
    }

    @Test
    @DisplayName("3,4,5 이동한 자동차를 멀리 이동한 순으로 정렬되는지 검사한다")
    void sorRacingCarEntriesIntoDescendingOrder() {
        String[] sample_data = {"a_car:4", "b_car:3", "c_car:5"};
        RacingCarEntries entries = makeRacingCarArrayListWithFixedDistance(sample_data);

        entries.sortReverse();

        CarName first = entries.get(0).getCarName();
        CarName second = entries.get(1).getCarName();
        CarName third = entries.get(2).getCarName();

        assertThat(first.toString()).isEqualTo("c_car");
        assertThat(second.toString()).isEqualTo("a_car");
        assertThat(third.toString()).isEqualTo("b_car");
    }

    @Test
    @DisplayName("3,5,5 이동한 자동차를 멀리 이동한 순으로 정렬되는지 검사한다")
    void sortRacingCarEntriesWithSameDistanceIntoDescendingOrder() {
        String[] sample_data = {"a_car:3", "b_car:5", "c_car:5"};
        RacingCarEntries entries = makeRacingCarArrayListWithFixedDistance(sample_data);

        entries.sortReverse();

        CarName first = entries.get(0).getCarName();
        CarName second = entries.get(1).getCarName();
        CarName third = entries.get(2).getCarName();

        assertThat(new String[] {"b_car", "c_car"}).contains(first.toString());
        assertThat(new String[] {"b_car", "c_car"}).contains(second.toString());
        assertThat(third.toString()).isEqualTo("a_car");
    }

    @Test
    @DisplayName("3,4,5 이동한 자동차 목록에서 우승자를 검사한다")
    void getWinnerNameInSoloWinner() {
        String[] sample_data = {"a_car:3", "b_car:4", "c_car:5"};
        RacingCarEntries entries = makeRacingCarArrayListWithFixedDistance(sample_data);

        CommaSeparatedCarNames winner_names = entries.getWinnerNames();

        assertThat(winner_names.toString()).isEqualTo("c_car");
    }

    @Test
    @DisplayName("3,5,5 이동한 자동차 목록에서 공동 우승자를 검사한다")
    void getWinnerNamesInJointWinners() {
        String[] sample_data = {"a_car:5", "b_car:4", "c_car:5"};
        RacingCarEntries entries = makeRacingCarArrayListWithFixedDistance(sample_data);

        CommaSeparatedCarNames winner_names = entries.getWinnerNames();

        assertThat(winner_names.toString()).isEqualTo("a_car,c_car");
    }

    @Test
    @DisplayName("5,5,5 이동한 자동차 목록에서 공동 우승자를 검사한다")
    void getWinnerNamesInAllWinners() {
        String[] sample_data = {"a_car:5", "b_car:5", "c_car:5"};
        RacingCarEntries entries = makeRacingCarArrayListWithFixedDistance(sample_data);

        CommaSeparatedCarNames winner_names = entries.getWinnerNames();

        assertThat(winner_names.toString()).isEqualTo("a_car,b_car,c_car");
    }
}

package racingcar;

import java.util.List;

public class RacingGame {
    private final List<Car> cars;
    private int attemptCount;

    public RacingGame(List<Car> cars, int attemptCount) {
        this.cars = cars;
        this.attemptCount = attemptCount;
    }

    public void startRace() {
        for (int i = 0; i < attemptCount; i++) {
            raceRound();
        }
        printWinners();
    }

    // 라운드 실행
    private void raceRound() {
        cars.forEach(Car::move);
        cars.forEach(car -> System.out.println(car.displayPosition()));
        System.out.println();
    }

    // 최종 우승자 출력
    private void printWinners() {
        int maxPosition = findMaxPosition();
        String winners = cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .map(Car::getName)
                .reduce((name1, name2) -> name1 + ", " + name2)
                .orElse("");

        System.out.println("최종 우승자 : " + winners);
    }

    // 최대 위치 찾기
    private int findMaxPosition() {
        return cars.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElseThrow(() -> new IllegalStateException("경주에 참여한 자동차가 없습니다."));
    }
}
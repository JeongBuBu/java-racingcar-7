package racingcar;


import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        // 사용자 입력 받기
        List<Car> cars = inputCarNames();
        int attemptCount = inputAttemptCount();

        // 경주 시작
        RacingGame racingGame = new RacingGame(cars, attemptCount);
        racingGame.startRace();
    }

    // 자동차 이름 입력 받기
    private static List<Car> inputCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분, 최대 5자)");
        String input = Console.readLine();

        String[] names = input.split(",");
        List<Car> cars = new ArrayList<>();

        for (String name : names) {
            String trimmedName = name.trim();
            if (trimmedName.isEmpty() || trimmedName.length() > 5) {
                throw new IllegalArgumentException("자동차 이름은 빈 문자열이거나 5자보다 길 수 없습니다.");
            }
            cars.add(new Car(trimmedName));
        }

        return cars;
    }

    // 시도할 횟수 입력 받기
    private static int inputAttemptCount() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        String input = Console.readLine();

        try {
            int count = Integer.parseInt(input);
            if (count <= 0) {
                throw new IllegalArgumentException("시도할 횟수는 1 이상의 숫자여야 합니다.");
            }
            return count;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("유효하지 않은 숫자입니다.");
        }
    }
}
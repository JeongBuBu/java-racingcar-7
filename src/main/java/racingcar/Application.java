package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Application {
    public static void main(String[] args) {
        // 1. 사용자 입력 받기
        String[] carNames = inputCarNames();
        int attemptCount = inputAttemptCount();

        // 2. 경주 시작
        int[] carPositions = new int[carNames.length];  // 각 자동차의 위치 저장

        for (int i = 0; i < attemptCount; i++) {
            raceRound(carNames, carPositions);  // 라운드 실행
        }

        // 3. 우승자 결정 및 출력
        printWinners(carNames, carPositions);
    }

    // 자동차 이름 입력 받기
    private static String[] inputCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분, 최대 5자)");
        String input = Console.readLine();

        // 공백 제거 및 유효성 검사
        String[] names = input.split(",");
        for (String name : names) {
            String trimmedName = name.trim(); // 앞뒤 공백 제거
            if (trimmedName.isEmpty()) {
                throw new IllegalArgumentException("자동차 이름은 빈 문자열일 수 없습니다.");
            }
            if (trimmedName.length() > 5) {
                throw new IllegalArgumentException("자동차 이름은 5자 이하여야 합니다. 현재 이름: " + trimmedName);
            }
        }

        return names;
    }

    // 시도할 횟수 입력 받기
    private static int inputAttemptCount() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        String input = Console.readLine();

        // 숫자로 변환 및 유효성 검사
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

    // 한 라운드 실행
    private static void raceRound(String[] carNames, int[] carPositions) {
        for (int i = 0; i < carNames.length; i++) {
            if (Randoms.pickNumberInRange(0, 9) >= 4) {
                carPositions[i]++;
            }
            System.out.println(carNames[i] + " : " + "-".repeat(carPositions[i]));
        }
        System.out.println();
    }

    // 최종 우승자 출력
    private static void printWinners(String[] carNames, int[] carPositions) {
        int maxPosition = findMaxPosition(carPositions);
        StringBuilder winners = new StringBuilder();

        for (int i = 0; i < carNames.length; i++) {
            if (carPositions[i] == maxPosition) {
                if (winners.length() > 0) {
                    winners.append(", ");
                }
                winners.append(carNames[i]);
            }
        }

        System.out.println("최종 우승자 : " + winners);
    }

    // 최대 위치 찾기
    private static int findMaxPosition(int[] carPositions) {
        int maxPosition = 0;
        for (int position : carPositions) {
            if (position > maxPosition) {
                maxPosition = position;
            }
        }
        return maxPosition;
    }
}
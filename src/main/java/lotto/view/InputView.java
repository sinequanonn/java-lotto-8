package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String NUMBER_REGEX = "\\d+";

    public Integer inputMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        String input = Console.readLine();
        validateIntegerNumber(input);
        return Integer.parseInt(input);
    }

    private void validateIntegerNumber(String input) {
        if (!input.matches(NUMBER_REGEX)) {
            throw new IllegalArgumentException("[ERROR] 정수이어야 합니다.");
        }
    }
}

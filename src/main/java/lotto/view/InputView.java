package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.validator.NumberValidator;

import java.util.Arrays;
import java.util.List;

import static lotto.validator.NumberValidator.validateDuplicateNumbers;
import static lotto.validator.NumberValidator.validateNumberRange;

public class InputView {
    public static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    public static final String INPUT_WINNING_LOTTO_MESSAGE = "당첨 번호를 입력해주세요.";
    public static final String INPUT_BONUS_NUMBER = "보너스 번호를 입력해주세요.";
    private static final String NUMBER_REGEX = "\\d+";

    public Integer inputMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        String input = Console.readLine();
        validateIntegerNumber(input);
        return Integer.parseInt(input);
    }

    public List<Integer> inputWinningLotto() {
        System.out.println(INPUT_WINNING_LOTTO_MESSAGE);
        String input = Console.readLine();
        List<Integer> winningNumbers = parseWinningNumbers(input);
        validateWinningNumbers(winningNumbers);
        return winningNumbers;
    }

    private void validateWinningNumbers(List<Integer> winningNumbers) {
        validateDuplicateNumbers(winningNumbers);
        validateNumberRange(winningNumbers);
    }

    private List<Integer> parseWinningNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .peek(this::validateIntegerNumber)
                .map(Integer::parseInt)
                .toList();
    }

    private void validateIntegerNumber(String input) {
        if (!input.matches(NUMBER_REGEX)) {
            throw new IllegalArgumentException("[ERROR] 정수이어야 합니다.");
        }
    }
}

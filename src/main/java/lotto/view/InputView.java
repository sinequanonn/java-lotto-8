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
    public static final String DELIMITER = ",";

    public void printInputMoneyMessage() {
        System.out.println(INPUT_MONEY_MESSAGE);
    }

    public Integer inputMoney() {
        String input = Console.readLine();
        validateIntegerNumber(input);
        return Integer.parseInt(input);
    }

    public void printInputWinningLotto() {
        printBlankLine();
        System.out.println(INPUT_WINNING_LOTTO_MESSAGE);
    }

    public List<Integer> inputWinningLotto() {
        String input = Console.readLine();
        List<Integer> winningNumbers = parseWinningNumbers(input);
        validateWinningNumbers(winningNumbers);
        return winningNumbers;
    }

    public void printInputBonusNumber() {
        printBlankLine();
        System.out.println(INPUT_BONUS_NUMBER);
    }

    public Integer inputBonusNumber() {
        String input = Console.readLine();
        validateIntegerNumber(input);
        return Integer.parseInt(input);
    }

    public void printBlankLine() {
        System.out.println();
    }

    private void validateWinningNumbers(List<Integer> winningNumbers) {
        validateDuplicateNumbers(winningNumbers);
        validateNumberRange(winningNumbers);
    }

    private List<Integer> parseWinningNumbers(String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .peek(this::validateIntegerNumber)
                .map(Integer::parseInt)
                .toList();
    }

    private void validateIntegerNumber(String input) {
        if (!input.matches(NUMBER_REGEX)) {
            throw new IllegalArgumentException("[ERROR] 입력 값은 정수이어야 합니다.");
        }
    }
}

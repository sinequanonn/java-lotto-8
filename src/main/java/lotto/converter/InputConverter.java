package lotto.converter;

import lotto.exception.ErrorMessage;

import java.util.Arrays;
import java.util.List;

public class InputConverter {
    private static final String DELIMITER_COMMA = ",";
    public static int convertStringToInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
        }
    }

    public static List<Integer> convertInputToListInteger(String input) {
        return Arrays.stream(input.split(DELIMITER_COMMA))
                .map(String::trim)
                .map(InputConverter::convertStringToInteger)
                .peek(InputConverter::validateLottoNumber)
                .toList();
    }

    public static void validateLottoNumber(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_NUMBER_RANGE.getMessage());
        }
    }
}

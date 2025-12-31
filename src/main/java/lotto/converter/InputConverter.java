package lotto.converter;

import lotto.exception.ErrorMessage;

public class InputConverter {
    public static int inputToPurchaseMoney(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
        }
    }
}

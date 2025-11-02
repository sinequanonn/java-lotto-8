package lotto.validator;

import lotto.exception.ErrorMessage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberValidator {
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final int LOTTO_MIN_NUMBER = 1;

    private NumberValidator() {
    }

    public static void validateNumberCount(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_SIZE.getMessage());
        }
    }
    
    public static void validateDuplicateNumbers(List<Integer> numbers) {
        Set<Integer> tempNumbers = new HashSet<>(numbers);
        if (tempNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_LOTTO_NUMBER.getMessage());
        }
    }

    public static void validateNumberRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            validateMinNumberRange(number);
            validateMaxNumberRange(number);
        }
    }

    public static void validateMaxNumberRange(Integer number) {
        if (number > LOTTO_MAX_NUMBER) {
            throw new IllegalArgumentException(ErrorMessage.OUT_OF_RANGE_MAX_NUMBER.getMessage());
        }
    }

    public static void validateMinNumberRange(Integer number) {
        if (number < LOTTO_MIN_NUMBER) {
            throw new IllegalArgumentException(ErrorMessage.OUT_OF_RANGE_MIN_NUMBER.getMessage());
        }
    }
}

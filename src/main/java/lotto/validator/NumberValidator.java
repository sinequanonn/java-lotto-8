package lotto.validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberValidator {
    public static final int LOTTO_NUMBER_COUNT = 6;
    public static final int LOTTO_MAX_NUMBER = 45;
    public static final int LOTTO_MIN_NUMBER = 1;

    private NumberValidator() {
    }

    public static void validateNumberCount(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }
    
    public static void validateDuplicateNumbers(List<Integer> numbers) {
        Set<Integer> tempNumbers = new HashSet<>(numbers);
        if (tempNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복된 숫자를 포함하면 안됩니다.");
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
            throw new IllegalArgumentException("[ERROR] 로또 번호는 45 초과이면 안됩니다.");
        }
    }

    public static void validateMinNumberRange(Integer number) {
        if (number < LOTTO_MIN_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1 미만이면 안됩니다.");
        }
    }
}

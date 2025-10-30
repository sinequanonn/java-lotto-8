package lotto.domain;

import java.util.List;

import static lotto.validator.NumberValidator.*;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateNumberCount(numbers);
        validateDuplicateNumbers(numbers);
        validateNumberRange(numbers);
    }
}

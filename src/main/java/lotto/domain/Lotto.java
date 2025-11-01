package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static lotto.validator.NumberValidator.*;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public int countMatch(List<Integer> winningNumber) {
        return (int) numbers.stream()
                .filter(winningNumber::contains)
                .count();
    }

    public boolean containsBonusNumber(Integer bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    public List<Integer> getSortedNumbers() {
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);
        return sortedNumbers;
    }

    private void validate(List<Integer> numbers) {
        validateNumberCount(numbers);
        validateDuplicateNumbers(numbers);
        validateNumberRange(numbers);
    }
}

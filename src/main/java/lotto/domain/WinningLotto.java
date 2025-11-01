package lotto.domain;

import java.util.List;

import static lotto.validator.NumberValidator.*;

public class WinningLotto {
    private final List<Integer> winningNumbers;
    private final Integer bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, Integer bonusNumber) {
        validate(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validate(List<Integer> winningNumbers, Integer bonusNumber) {
        validateBonusNumberNotDuplicate(winningNumbers, bonusNumber);
        validateWinningNumbers(winningNumbers);
        validateBonusNUmber(bonusNumber);
    }

    private void validateBonusNumberNotDuplicate(List<Integer> winningNumbers, Integer bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    private void validateWinningNumbers(List<Integer> winningNumbers) {
        validateNumberCount(winningNumbers);
        validateDuplicateNumbers(winningNumbers);
        validateNumberRange(winningNumbers);
    }

    private void validateBonusNUmber(Integer bonusNumber) {
        validateMaxNumberRange(bonusNumber);
        validateMinNumberRange(bonusNumber);
    }
}

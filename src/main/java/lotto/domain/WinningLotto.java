package lotto.domain;

import lotto.exception.ErrorMessage;

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

    public LottoRank match(Lotto lotto) {
        int matchCount = lotto.countMatch(winningNumbers);
        boolean matchBonusNumber = lotto.containsBonusNumber(bonusNumber);
        return LottoRank.of(matchCount, matchBonusNumber);
    }

    private void validate(List<Integer> winningNumbers, Integer bonusNumber) {
        validateBonusNumberNotDuplicate(winningNumbers, bonusNumber);
        validateWinningNumbers(winningNumbers);
        validateBonusNUmber(bonusNumber);
    }

    private void validateBonusNumberNotDuplicate(List<Integer> winningNumbers, Integer bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_BONUS_NUMBER.getMessage());
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

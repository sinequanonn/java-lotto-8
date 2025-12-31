package lotto.domain;

public class WinningLotto {
    private final Lotto lotto;
    private final int bonusNumber;

    public WinningLotto(Lotto lotto, int bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public int matchCount(Lotto lotto) {
        return this.lotto.matchCount(lotto.getNumbers());
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}

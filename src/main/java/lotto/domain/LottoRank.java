package lotto.domain;

public enum LottoRank {
    FIRST(2_000_000_000, "6개 일치"),
    SECOND(30_000_000,"5개 일치, 보너스 볼 일치"),
    THIRD(1_500_000, "5개 일치"),
    FOURTH(50_000, "4개 일치"),
    FIFTH(5_000, "3개 일치"),
    NONE(0, "0개 일치");

    private final long money;
    private final String message;

    LottoRank(long money, String message) {
        this.money = money;
        this.message = message;
    }

    public static LottoRank of(int matchingCount, boolean bonusMatch) {
        if (matchingCount == 6) {
            return FIRST;
        }
        if (matchingCount == 5) {
            if (bonusMatch) {
                return SECOND;
            }
            return THIRD;
        }
        if (matchingCount == 4) {
            return FOURTH;
        }
        if (matchingCount == 3) {
            return FIFTH;
        }
        return NONE;
    }

    public long getMoney() {
        return money;
    }

    public String getMessage() {
        return message;
    }
}

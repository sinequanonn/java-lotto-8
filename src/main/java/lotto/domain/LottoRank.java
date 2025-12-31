package lotto.domain;

public enum LottoRank {
    FIRST("6개 일치", 2_000_000_000),
    SECOND("5개 일치, 보너스 볼 일치", 30_000_000),
    THIRD("5개 일치", 1_500_000),
    FOURTH("4개 일치", 50_000),
    FIFTH("3개 일치", 5_000),
    NONE("0개 일치", 0);

    private String message;
    private long prize;

    LottoRank(String message, long prize) {
        this.message = message;
        this.prize = prize;
    }

    public static LottoRank of(int matchNumbers, boolean matchBonus) {
        if (matchNumbers == 6) {
            return FIRST;
        }
        if (matchNumbers == 5 && matchBonus) {
            return SECOND;
        }
        if (matchNumbers == 5) {
            return THIRD;
        }
        if (matchNumbers == 4) {
            return FOURTH;
        }
        if (matchNumbers == 3) {
            return FIFTH;
        }
        return NONE;
    }

    public String getMessage() {
        return message;
    }

    public long getPrize() {
        return prize;
    }
}

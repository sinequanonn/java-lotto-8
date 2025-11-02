package lotto.domain;

import lotto.exception.ErrorMessage;

public class LottoCoin {
    private static final int LOTTO_PRICE = 1000;

    private int coinAmount;
    private int usedMoney;

    public LottoCoin(int money) {
        validateMoney(money);
        coinAmount = money / LOTTO_PRICE;
        usedMoney = money;
    }

    public int getCoinAmount() {
        return coinAmount;
    }

    public int getUsedMoney() {
        return usedMoney;
    }

    private void validateMoney(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_PRICE.getMessage());
        }
    }
}

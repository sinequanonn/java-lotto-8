package lotto.domain;

import lotto.exception.ErrorMessage;

public class Money {
    private static final int LOTTO_PRICE = 1000;

    private final int money;

    public Money(int money) {
        validate(money);
        this.money = money;
    }

    public int purchasedAmount() {
        return money / LOTTO_PRICE;
    }

    private void validate(int money) {
        if (money < 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_RANGE.getMessage());
        }
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_PRICE.getMessage());
        }
    }

    public int getMoney() {
        return money;
    }
}

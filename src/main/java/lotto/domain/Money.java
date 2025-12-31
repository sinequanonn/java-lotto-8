package lotto.domain;

import lotto.exception.ErrorMessage;

public class Money {
    private static final int LOTTO_PRICE = 1000;

    private final int amount;

    public Money(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_RANGE.getMessage());
        }
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_PRICE.getMessage());
        }
    }
}

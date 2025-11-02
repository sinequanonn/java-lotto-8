package lotto.domain;

import lotto.exception.ErrorMessage;

public class LottoMachine {
    private static final int LOTTO_PRICE = 1000;

    private final RandomNumberGenerator generator;
    private int coin;
    private int usedMoney;

    public LottoMachine(RandomNumberGenerator generator) {
        this.generator = generator;
        this.coin = 0;
        this.usedMoney = 0;
    }

    public void exchangeForCoins(int money) {
        validateExchangeAmount(money);
        int purchasedCoins = money / LOTTO_PRICE;
        coin += purchasedCoins;
        usedMoney += money;
    }

    public Lotto issueLotto() {
        return new Lotto(generator.generate());
    }

    public void useCoin() {
        coin--;
    }

    public boolean hasCoins() {
        return coin > 0;
    }

    public int getUsedMoney() {
        return usedMoney;
    }

    private void validateExchangeAmount(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_PRICE.getMessage());
        }
    }
}

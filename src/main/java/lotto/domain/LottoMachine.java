package lotto.domain;

import lotto.Lotto;
import lotto.RandomNumberGenerator;

public class LottoMachine {
    private static final int LOTTO_PRICE = 1000;

    private final RandomNumberGenerator generator;
    private int coin;

    public LottoMachine(RandomNumberGenerator generator) {
        this.generator = generator;
        this.coin = 0;
    }

    public void exchangeForCoins(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위여야 합니다.");
        }
        int purchasedCoins = money / LOTTO_PRICE;
        coin += purchasedCoins;
    }

    public Lotto issueLotto() {
        return new Lotto(generator.generate());
    }

    public boolean hasCoins() {
        return coin > 0;
    }
}

package lotto.domain;

public class LottoMachine {
    private static final int LOTTO_PRICE = 1000;

    private int coin;

    public LottoMachine() {
        this.coin = 0;
    }

    public void exchangeForCoins(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위여야 합니다.");
        }
        int purchasedCoins = money / LOTTO_PRICE;
        coin += purchasedCoins;
    }

    public boolean hasCoins() {
        return this.coin > 0;
    }
}

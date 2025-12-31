package lotto.view;

import lotto.domain.Lotto;

import java.util.List;

public class OutputView {
    private static final String PURCHASED_MESSAGE = "%d개를 구매했습니다.\n";

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printPurchasedLotto(int count) {
        System.out.printf(PURCHASED_MESSAGE, count);
    }

    public void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }
}

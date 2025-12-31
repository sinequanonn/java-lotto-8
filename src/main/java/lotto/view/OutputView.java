package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;

import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String PURCHASED_MESSAGE = "%d개를 구매했습니다.\n";
    private static final String RESULT_MESSAGE = "당첨 통계";
    private static final String LINE = "---";
    private static final String RESULT_FORMAT = "%s (%,d원) - %d개\n";

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

    public void printResult(Map<LottoRank, Integer> result) {
        System.out.println(RESULT_MESSAGE);
        System.out.println(LINE);
        printResultLine(LottoRank.FIFTH, result);
        printResultLine(LottoRank.FOURTH, result);
        printResultLine(LottoRank.THIRD, result);
        printResultLine(LottoRank.SECOND, result);
        printResultLine(LottoRank.FIRST, result);
    }

    private void printResultLine(LottoRank lottoRank, Map<LottoRank, Integer> result) {
        int count = result.getOrDefault(lottoRank, 0);
        System.out.printf(RESULT_FORMAT,
                lottoRank.getMessage(), lottoRank.getPrize(), count);
    }
}

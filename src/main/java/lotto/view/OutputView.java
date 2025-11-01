package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;

import java.util.List;
import java.util.Map;

public class OutputView {
    public static final String PURCHASE_MESSAGE = "개를 구매했습니다.";
    public static final String STATISTICS_MESSAGE = "당첨 통계";
    public static final String STATISTICS_DIVIDER = "---";
    private static final String RANK_RESULT_FORMAT = "%s (%,d원) - %d개";
    private static final String PROFIT_RATE_FORMAT = "총 수익률은 %.1f%%입니다.";
    private static final String NEW_LINE = "\n";

    public void printPurchasedLotto(int lottoCount) {
        System.out.println(lottoCount + PURCHASE_MESSAGE);
    }

    public void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getSortedNumbers());
        }
    }

    public void printWinningStatistics(Map<LottoRank, Integer> result) {
        System.out.println();
        System.out.println(STATISTICS_MESSAGE);
        System.out.println(STATISTICS_DIVIDER);
        printRankResult(LottoRank.FIFTH, result);
        printRankResult(LottoRank.FOURTH, result);
        printRankResult(LottoRank.THIRD, result);
        printRankResult(LottoRank.SECOND, result);
        printRankResult(LottoRank.FIRST, result);
    }

    private void printRankResult(LottoRank rank, Map<LottoRank, Integer> result) {
        int count = result.getOrDefault(rank, 0);
        System.out.printf(RANK_RESULT_FORMAT + NEW_LINE,
                rank.getMessage(), rank.getMoney(), count);
    }

    public void printProfitRate(double rate) {
        System.out.printf(PROFIT_RATE_FORMAT + NEW_LINE, rate);
    }
}

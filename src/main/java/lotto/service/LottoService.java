package lotto.service;

import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoRank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoService {
    private final LottoMachine lottoMachine;

    public LottoService(LottoMachine lottoMachine) {
        this.lottoMachine = lottoMachine;
    }

    public List<Lotto> purchaseLottos(int money) {
        lottoMachine.exchangeForCoins(money);

        List<Lotto> lottos = new ArrayList<>();
        while (lottoMachine.hasCoins()) {
            lottos.add(lottoMachine.issueLotto());
            lottoMachine.useCoin();
        }
        return lottos;
    }

    public Map<LottoRank, Integer> calculateResult(List<Lotto> lottos, List<Integer> winningNumber, BonusNumber bonusNumber) {
        Map<LottoRank, Integer> result = new HashMap<>();

        for (Lotto lotto : lottos) {
            int matchCount = lotto.countMatch(winningNumber);
            boolean matchBonusNumber = lotto.containsBonusNumber(bonusNumber);

            LottoRank rank = LottoRank.of(matchCount, matchBonusNumber);
            result.put(rank, result.getOrDefault(rank, 0) + 1);
        }
        return result;
    }

    public double calculateProfitRate(Map<LottoRank, Integer> result, int purchaseAmount) {
        double totalProfit = 0;
        for (LottoRank lottoRank : result.keySet()) {
            Integer count = result.get(lottoRank);
            totalProfit += lottoRank.getMoney() * count;
        }
        return Math.round((totalProfit / purchaseAmount) * 1000) / 10.0;
    }
}

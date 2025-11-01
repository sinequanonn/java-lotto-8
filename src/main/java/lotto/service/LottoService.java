package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoRank;
import lotto.domain.WinningLotto;

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

    public Map<LottoRank, Integer> calculateResult(List<Lotto> lottos, WinningLotto winningLotto) {
        Map<LottoRank, Integer> result = new HashMap<>();

        for (Lotto lotto : lottos) {
            LottoRank rank = winningLotto.match(lotto);
            result.put(rank, result.getOrDefault(rank, 0) + 1);
        }
        return result;
    }

    public double calculateProfitRate(Map<LottoRank, Integer> result) {
        int purchaseAmount = lottoMachine.getUsedMoney();
        double totalProfit = 0;
        for (LottoRank lottoRank : result.keySet()) {
            Integer count = result.get(lottoRank);
            totalProfit += lottoRank.getMoney() * count;
        }
        return Math.round((totalProfit / purchaseAmount) * 1000) / 10.0;
    }
}

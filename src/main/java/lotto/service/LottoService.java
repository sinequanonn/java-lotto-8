package lotto.service;

import lotto.domain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoService {
    private final LottoMachine lottoMachine;

    public LottoService(LottoMachine lottoMachine) {
        this.lottoMachine = lottoMachine;
    }

    public LottoCoin exchangeCoin(int money) {
        return new LottoCoin(money);
    }

    public List<Lotto> purchaseLottos(LottoCoin lottoCoin) {
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoCoin.getCoinAmount(); i++) {
            lottos.add(lottoMachine.issueLotto());
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

    public double calculateProfitRate(Map<LottoRank, Integer> result, LottoCoin lottoCoin) {
        int purchaseAmount = lottoCoin.getUsedMoney();
        double totalProfit = 0;
        for (LottoRank lottoRank : result.keySet()) {
            Integer count = result.get(lottoRank);
            totalProfit += lottoRank.getMoney() * count;
        }
        return Math.round((totalProfit / purchaseAmount) * 1000) / 10.0;
    }
}

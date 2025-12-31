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

    public List<Lotto> purchaseLotto(Money money) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < money.purchasedAmount(); i++) {
            lottos.add(lottoMachine.issue());
        }
        return lottos;
    }

    public Map<LottoRank, Integer> calculateResult(List<Lotto> lottos, WinningLotto winningLotto) {
        Map<LottoRank, Integer> result = new HashMap<>();

        for (Lotto lotto : lottos) {
            int matchNumbers = winningLotto.matchCount(lotto);
            boolean matchBonus = lotto.contains(winningLotto.getBonusNumber());
            LottoRank rank = LottoRank.of(matchNumbers, matchBonus);
            result.put(rank, result.getOrDefault(rank, 0) + 1);
        }
        return result;
    }

    public double calculateProfit(Map<LottoRank, Integer> rank, Money money) {
        double totalProfit = 0;
        for (LottoRank lottoRank : rank.keySet()) {
            totalProfit += lottoRank.getPrize() * rank.getOrDefault(lottoRank, 0);
        }
        return Math.round((totalProfit / money.getMoney()) * 1000) / 10.0;
    }
}

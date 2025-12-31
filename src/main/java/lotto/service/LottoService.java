package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.Money;

import java.util.ArrayList;
import java.util.List;

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
}

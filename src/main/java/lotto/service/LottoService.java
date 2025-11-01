package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoMachine;

import java.util.ArrayList;
import java.util.List;

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
}

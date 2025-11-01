package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.WinningLotto;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Map;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void run() {
        Integer money = inputView.inputMoney();
        List<Lotto> lottos = lottoService.purchaseLottos(money);
        outputView.printPurchasedLotto(lottos.size());
        outputView.printLottos(lottos);

        List<Integer> winningNumbers = inputView.inputWinningLotto();
        Integer bonusNumber = inputView.inputBonusNumber();
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        Map<LottoRank, Integer> result = lottoService.calculateResult(lottos, winningLotto);
        double profitRate = lottoService.calculateProfitRate(result, money);

        outputView.printWinningStatistics(result);
        outputView.printProfitRate(profitRate);
    }
}

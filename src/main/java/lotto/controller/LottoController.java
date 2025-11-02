package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoCoin;
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
        LottoCoin lottoCoin = inputMoneyAndCreateLottoCoins();

        List<Lotto> lottos = lottoService.purchaseLottos(lottoCoin);
        printLottos(lottos);

        WinningLotto winningLotto = getWinningLotto();
        printResults(lottos, winningLotto, lottoCoin);
    }

    private LottoCoin inputMoneyAndCreateLottoCoins() {
        inputView.printInputMoneyMessage();
        while (true) {
            try {
                Integer money = inputView.inputMoney();
                return lottoService.exchangeCoin(money);
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception.getMessage());
            }
        }
    }

    private void printLottos(List<Lotto> lottos) {
        outputView.printPurchasedLotto(lottos.size());
        outputView.printLottos(lottos);
    }

    private WinningLotto getWinningLotto() {
        List<Integer> winningNumbers = inputWinningNumbers();
        return inputBonusNumberAndGetWinningLotto(winningNumbers);
    }

    private List<Integer> inputWinningNumbers() {
        inputView.printInputWinningLotto();
        while (true) {
            try {
                return inputView.inputWinningLotto();
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception.getMessage());
            }
        }
    }

    private WinningLotto inputBonusNumberAndGetWinningLotto(List<Integer> winningNumbers) {
        inputView.printInputBonusNumber();
        while (true) {
            try {
                Integer bonusNumber = inputView.inputBonusNumber();
                return lottoService.createWinningLotto(winningNumbers, bonusNumber);
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception.getMessage());
            }
        }
    }

    private void printResults(List<Lotto> lottos, WinningLotto winningLotto, LottoCoin lottoCoin) {
        Map<LottoRank, Integer> result = lottoService.calculateResult(lottos, winningLotto);
        outputView.printWinningStatistics(result);

        double profitRate = lottoService.calculateProfitRate(result, lottoCoin);
        outputView.printProfitRate(profitRate);
    }
}

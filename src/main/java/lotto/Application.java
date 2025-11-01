package lotto;

import lotto.controller.LottoController;
import lotto.domain.LottoMachine;
import lotto.domain.RandomNumberGenerator;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        LottoMachine lottoMachine = new LottoMachine(randomNumberGenerator);
        LottoService lottoService = new LottoService(lottoMachine);
        LottoController lottoController = new LottoController(inputView, outputView, lottoService);

        lottoController.run();
    }
}

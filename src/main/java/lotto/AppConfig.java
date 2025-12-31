package lotto;

import lotto.controller.LottoController;
import lotto.domain.LottoMachine;
import lotto.domain.NumberGenerator;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class AppConfig {
    private InputView inputView;
    private OutputView outputView;
    private LottoController lottoController;
    private LottoService lottoService;

    private InputView inputView() {
        if (inputView == null) {
            inputView = new InputView();
        }
        return inputView;
    }

    private OutputView outputView() {
        if (outputView == null) {
            outputView = new OutputView();
        }
        return outputView;
    }

    public LottoController lottoController() {
        if (lottoController == null) {
            lottoController = new LottoController(inputView(), outputView(), lottoService());
        }
        return lottoController;
    }

    private LottoService lottoService() {
        if (lottoService == null) {
            lottoService = new LottoService(new LottoMachine(new NumberGenerator()));
        }
        return lottoService;
    }
}

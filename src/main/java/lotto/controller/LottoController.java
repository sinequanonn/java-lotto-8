package lotto.controller;

import lotto.converter.InputConverter;
import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

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
        Money money = inputPurchasedMoney();
        List<Lotto> lottos = lottoService.purchaseLotto(money);
    }

    private <T> T checkValidInput(Supplier<T> inputSupplier) {
        while (true) {
            try {
                return inputSupplier.get();
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception.getMessage());
            }
        }
    }

    private Money inputPurchasedMoney() {
        return checkValidInput(() -> {
            int amount = InputConverter.inputToPurchaseMoney(inputView.inputPurchaseMoney());

            return new Money(amount);
        });
    }
}

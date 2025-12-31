package lotto.controller;

import lotto.converter.InputConverter;
import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.exception.ErrorMessage;
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
        outputView.printPurchasedLotto(lottos.size());
        outputView.printLottos(lottos);

        Lotto lotto = inputWinngingLotto();
        WinningLotto winningLotto = inputBonusNumber(lotto);

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
            int amount = InputConverter.convertStringToInteger(inputView.inputPurchaseMoney());

            return new Money(amount);
        });
    }

    private Lotto inputWinngingLotto() {
        return checkValidInput(() -> {
            String input = inputView.inputWinningLottoNumbers();
            List<Integer> numbers = InputConverter.convertInputToListInteger(input);
            return new Lotto(numbers);
        });
    }

    private WinningLotto inputBonusNumber(Lotto lotto) {
        return checkValidInput(() -> {
            String input = inputView.inputBonusNumber();
            int bonusNumber = InputConverter.convertStringToInteger(input);
            bonusNumber = InputConverter.validateLottoNumber(bonusNumber);
            if (lotto.contains(bonusNumber)) {
                throw new IllegalArgumentException(ErrorMessage.DUPLICATED_NUMBER.getMessage());
            }
            return new WinningLotto(lotto, bonusNumber);
        });
    }
}

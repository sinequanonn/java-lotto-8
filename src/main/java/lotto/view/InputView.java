package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String MONEY_INPUT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String WINNING_LOTTO_INPUT_MESSAGE = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_INPUT_MESSAGE = "보너스 번호를 입력해 주세요.";

    public String inputPurchaseMoney() {
        System.out.println(MONEY_INPUT_MESSAGE);
        return Console.readLine();
    }

    public void inputWinningLottoNumber() {
        System.out.println(WINNING_LOTTO_INPUT_MESSAGE);
    }

    public void inputBonusNumber() {
        System.out.println(BONUS_NUMBER_INPUT_MESSAGE);
    }

    public String input() {
        return Console.readLine();
    }
}

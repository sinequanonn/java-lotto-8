package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LottoMachineTest {
    private LottoMachine lottoMachine;

    @BeforeEach
    void setUp() {
        lottoMachine = new LottoMachine();
    }

    @Test
    void 로또기계에_구입금액을_넣으면_로또를_발행할_수_있는_코인을_생성한다() {
        //given
        int money = 1000;

        //when
        lottoMachine.exchangeForCoins(money);

        //then
        Assertions.assertThat(lottoMachine.hasCoins()).isTrue();
    }

    @Test
    void 구입금액이_로또금원_단위가_아니면_예외가_발생한다() {
        //given
        int money = 1500;

        //when & then
        Assertions.assertThatThrownBy(() -> lottoMachine.exchangeForCoins(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 1000원 단위여야 합니다.");
    }
}

package lotto.domain;

import lotto.Lotto;
import lotto.RandomNumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoMachineTest {
    private LottoMachine lottoMachine;

    @BeforeEach
    void setUp() {
        lottoMachine = new LottoMachine(new RandomNumberGenerator());
    }

    @Test
    void 로또기계에_구입금액을_넣으면_로또를_발행할_수_있는_코인을_생성한다() {
        //given
        int money = 1000;

        //when
        lottoMachine.exchangeForCoins(money);

        //then
        assertThat(lottoMachine.hasCoins()).isTrue();
    }

    @Test
    void 구입금액이_로또금원_단위가_아니면_예외가_발생한다() {
        //given
        int money = 1500;

        //when & then
        assertThatThrownBy(() -> lottoMachine.exchangeForCoins(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 1000원 단위여야 합니다.");
    }

    @Test
    void 코인이_있으면_로또를_발행할_수_있다() {
        //given
        lottoMachine.exchangeForCoins(1000);

        //when
        Lotto lotto = lottoMachine.issueLotto();

        //then
        assertThat(lotto).isNotNull();
        assertThat(lotto).isInstanceOf(Lotto.class);
    }
}

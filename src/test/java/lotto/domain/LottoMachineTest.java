package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoMachineTest {
    private LottoMachine lottoMachine;

    @BeforeEach
    void setUp() {
        lottoMachine = new LottoMachine(new RandomNumberGenerator());
    }

    @Test
    void 로또를_한_개_생생한다() {
        //when
        Lotto lotto = lottoMachine.issueLotto();

        //then
        assertThat(lotto).isInstanceOf(Lotto.class);
    }
}

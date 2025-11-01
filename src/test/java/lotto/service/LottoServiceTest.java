package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.RandomNumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoServiceTest {
    LottoMachine lottoMachine;
    LottoService lottoService;

    @BeforeEach
    void setUp() {
        lottoMachine = new LottoMachine(new RandomNumberGenerator());
        lottoService = new LottoService(lottoMachine);
    }

    @ParameterizedTest
    @CsvSource({"1000, 1", "2000, 2", "3000, 3", "5000, 5"})
    void 구입_금액만큼_로또를_발행한다(int money, int expectedCount) {
        //when
        List<Lotto> lottos = lottoService.purchaseLottos(money);

        //then
        assertThat(lottos).hasSize(expectedCount);
    }

    @ParameterizedTest
    @ValueSource(ints = {1500, 3500, 5500})
    void 구입_금액이_로또_가격으로_나누어_떨어지지_않으면_예외가_발생한다(int money) {
        //when&then
        assertThatThrownBy(() -> lottoService.purchaseLottos(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 1000원 단위여야 합니다.");
    }
}

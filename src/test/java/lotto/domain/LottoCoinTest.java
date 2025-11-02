package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoCoinTest {
    @Test
    void 구입금액을_넣으면_로또를_발행할_수_있는_코인을_생성한다() {
        //given
        int money = 1000;

        //when
        LottoCoin lottoCoin = new LottoCoin(money);

        //then
        Assertions.assertThat(lottoCoin).isInstanceOf(LottoCoin.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1500, 2500, 3500})
    void 구입금액이_로또금원_단위가_아니면_예외가_발생한다(int money) {
        //when & then
        assertThatThrownBy(() -> new LottoCoin(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 1000원 단위여야 합니다.");
    }
}

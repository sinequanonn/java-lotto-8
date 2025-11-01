package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BonusNumberTest {
    @ParameterizedTest
    @ValueSource(ints={5, 10, 15, 25})
    void 보너스_번호를_생성한다(int number) {
        //when
        BonusNumber bonusNumber = new BonusNumber(number);

        //then
        assertThat(bonusNumber).isInstanceOf(BonusNumber.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -10, -150})
    void 보너스_번호가_1미민인_경우_예외를_발생한다(int number) {
        assertThatThrownBy(() -> new BonusNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호 숫자 범위는 1에서 45 사이어야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {46, 48, 100, 150})
    void 보너스_번호가_45초과인_경우_예외를_발생한다(int number) {
        assertThatThrownBy(() -> new BonusNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호 숫자 범위는 1에서 45 사이어야 합니다.");
    }
}

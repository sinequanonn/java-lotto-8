package lotto.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.ValueSources;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoRankTest {
    @Test
    void 로또번호_6개_일칠하면_1등이다() {
        //given&when
        LottoRank rank = LottoRank.of(6, false);

        assertThat(rank).isEqualTo(LottoRank.FIRST);
    }

    @Test
    void 로또번호_5개_일차하고_보너스볼이_일치하면_2등이다() {
        //given&when
        LottoRank rank = LottoRank.of(5, true);

        assertThat(rank).isEqualTo(LottoRank.SECOND);
    }

    @Test
    void 로또번호_5개_일차하고_보너스볼이_일치하지_않으면_3등이다() {
        //given&when
        LottoRank rank = LottoRank.of(5, false);

        assertThat(rank).isEqualTo(LottoRank.THIRD);
    }

    @Test
    void 로또번호_4개_일차하면_4등이다() {
        //given&when
        LottoRank rank = LottoRank.of(4, false);

        assertThat(rank).isEqualTo(LottoRank.FOURTH);
    }

    @Test
    void 로또번호_3개_일차하면_5등이다() {
        //given&when
        LottoRank rank = LottoRank.of(3, false);

        assertThat(rank).isEqualTo(LottoRank.FIFTH);
    }

    @ParameterizedTest
    @ValueSource(ints ={2, 1, 0})
    void 로또번호_2개_이하로_일치하면_꽝이다(int count) {
        //given&when
        LottoRank rank = LottoRank.of(count, false);

        assertThat(rank).isEqualTo(LottoRank.NONE);
    }
}

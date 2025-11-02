package lotto.service;

import lotto.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

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
        LottoCoin lottoCoin = new LottoCoin(money);
        List<Lotto> lottos = lottoService.purchaseLottos(lottoCoin);

        //then
        assertThat(lottos).hasSize(expectedCount);
    }

    @Test
    void 로또_번호가_6개_일치하면_1등이다() {
        //given
        List<Lotto> lottos = List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
        List<Integer> winningNumber = List.of(1, 2, 3, 4, 5, 6);
        Integer bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningNumber, bonusNumber);

        //when
        Map<LottoRank, Integer> result = lottoService.calculateResult(lottos, winningLotto);

        //then
        assertThat(result.get(LottoRank.FIRST)).isEqualTo(1);
    }

    @Test
    void 로또_번호가_5개_일치하고_보너스_번호도_일치하면_2등이다() {
        //given
        List<Lotto> lottos = List.of(new Lotto(List.of(1, 2, 3, 4, 5, 7)));
        List<Integer> winningNumber = List.of(1, 2, 3, 4, 5, 6);
        Integer bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningNumber, bonusNumber);

        //when
        Map<LottoRank, Integer> result = lottoService.calculateResult(lottos, winningLotto);

        //then
        assertThat(result.get(LottoRank.SECOND)).isEqualTo(1);
    }

    @Test
    void 로또_번호가_5개_일치하고_보너스_번호가_일치하지_않으면_3등이다() {
        //given
        List<Lotto> lottos = List.of(new Lotto(List.of(1, 2, 3, 4, 5, 8)));
        List<Integer> winningNumber = List.of(1, 2, 3, 4, 5, 6);
        Integer bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningNumber, bonusNumber);

        //when
        Map<LottoRank, Integer> result = lottoService.calculateResult(lottos, winningLotto);

        //then
        assertThat(result.get(LottoRank.THIRD)).isEqualTo(1);
    }

    @Test
    void 로또_번호가_4개_일치하면_4등이다() {
        //given
        List<Lotto> lottos = List.of(new Lotto(List.of(1, 2, 3, 4, 8, 9)));
        List<Integer> winningNumber = List.of(1, 2, 3, 4, 5, 6);
        Integer bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningNumber, bonusNumber);

        //when
        Map<LottoRank, Integer> result = lottoService.calculateResult(lottos, winningLotto);

        //then
        assertThat(result.get(LottoRank.FOURTH)).isEqualTo(1);
    }

    @Test
    void 로또_번호가_3개_일치하면_5등이다() {
        //given
        List<Lotto> lottos = List.of(new Lotto(List.of(1, 2, 3, 8, 9, 10)));
        List<Integer> winningNumber = List.of(1, 2, 3, 4, 5, 6);
        Integer bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningNumber, bonusNumber);

        //when
        Map<LottoRank, Integer> result = lottoService.calculateResult(lottos, winningLotto);

        //then
        assertThat(result.get(LottoRank.FIFTH)).isEqualTo(1);
    }

    @Test
    void 로또_번호가_2개_이하로_일치하면_꽝이다() {
        //given
        List<Lotto> lottos = List.of(new Lotto(List.of(1, 2, 7, 8, 9, 10)));
        List<Integer> winningNumber = List.of(1, 2, 13, 14, 15, 16);
        Integer bonusNumber = 45;
        WinningLotto winningLotto = new WinningLotto(winningNumber, bonusNumber);

        //when
        Map<LottoRank, Integer> result = lottoService.calculateResult(lottos, winningLotto);

        //then
        assertThat(result.get(LottoRank.NONE)).isEqualTo(1);
    }

    @Test
    void 같은_등수가_여러_개_있을_떄_개수를_센다() {
        //given
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 8, 9, 10)),
                new Lotto(List.of(1, 2, 3, 8, 9, 10)),
                new Lotto(List.of(1, 2, 3, 8, 9, 10)));
        List<Integer> winningNumber = List.of(1, 2, 3, 4, 5, 6);
        Integer bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningNumber, bonusNumber);

        //when
        Map<LottoRank, Integer> result = lottoService.calculateResult(lottos, winningLotto);

        //then
        assertThat(result.get(LottoRank.FIFTH)).isEqualTo(3);
    }

    @Test
    void 구입금액_55000원으로_4등_1개와_5등_1개가_당첨되면_수익률은_100퍼센트이다() {
        //given
        Map<LottoRank, Integer> result = new HashMap<>();
        result.put(LottoRank.FOURTH, 1);
        result.put(LottoRank.FIFTH, 1);
        LottoCoin lottoCoin = new LottoCoin(55000);
        System.out.println("lottoCoin = " + lottoCoin.getCoinAmount());
        System.out.println("lottoCoin = " + lottoCoin.getUsedMoney());

        //when
        double profitRate = lottoService.calculateProfitRate(result, lottoCoin);

        //then
        assertThat(profitRate).isEqualTo(100.0);
    }

    @Test
    void 당첨된_경우가_없을_때_수익률은_0퍼센트이다() {
        //given
        LottoCoin lottoCoin = new LottoCoin(1000);
        Map<LottoRank, Integer> result = new HashMap<>();

        //when
        double profitRate = lottoService.calculateProfitRate(result, lottoCoin);

        //then
        assertThat(profitRate).isEqualTo(0.0);
    }
}

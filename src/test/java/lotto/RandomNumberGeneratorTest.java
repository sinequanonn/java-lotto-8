package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomNumberGeneratorTest {

    RandomNumberGenerator generator;

    @BeforeEach
    void setUp() {
        generator = new RandomNumberGenerator();
    }

    @Test
    void 랜덤숫자_6개를_생성한다() {
        //when
        List<Integer> numbers = generator.generate();

        //then
        assertThat(numbers).hasSize(6);
    }

    @Test
    void 생성된_숫자에는_중복이_포함하지_않는다() {
        //when
        List<Integer> numbers = generator.generate();

        //then
        assertThat(numbers).doesNotHaveDuplicates();
    }

    @Test
    void 생성된_숫자는_모두_1이상이어야_한다() {
        //when
        List<Integer> numbers = generator.generate();

        //then
        for (Integer number : numbers) {
            assertThat(number).isGreaterThanOrEqualTo(1);
        }
    }

    @Test
    void 생성된_숫자는_모두_45이하이어야_한다() {
        //when
        List<Integer> numbers = generator.generate();

        //then
        for (Integer number : numbers) {
            assertThat(number).isLessThanOrEqualTo(45);
        }
    }


}

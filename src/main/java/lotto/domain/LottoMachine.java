package lotto.domain;

public class LottoMachine {
    private final NumberGenerator generator;

    public LottoMachine(NumberGenerator generator) {
        this.generator = generator;
    }

    public Lotto issue() {
        return new Lotto(generator.generate());
    }
}

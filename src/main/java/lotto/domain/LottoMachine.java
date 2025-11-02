package lotto.domain;

public class LottoMachine {
    private final RandomNumberGenerator generator;

    public LottoMachine(RandomNumberGenerator generator) {
        this.generator = generator;
    }

    public Lotto issueLotto() {
        return new Lotto(generator.generate());
    }
}

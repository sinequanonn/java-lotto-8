package lotto.domain;

public class BonusNumber {
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;

    private Integer number;

    public BonusNumber(Integer number) {
        validate(number);
        this.number = number;
    }

    private void validate(Integer number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호 숫자 범위는 1에서 45 사이어야 합니다.");
        }
    }

    public Integer getNumber() {
        return number;
    }
}

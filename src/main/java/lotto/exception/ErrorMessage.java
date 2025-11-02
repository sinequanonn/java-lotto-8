package lotto.exception;

public enum ErrorMessage {
    INVALID_LOTTO_PRICE("[ERROR] 구입 금액은 1000원 단위여야 합니다."),
    INVALID_LOTTO_SIZE("[ERROR] 로또 번호는 6개여야 합니다."),
    DUPLICATE_BONUS_NUMBER("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다."),
    DUPLICATE_LOTTO_NUMBER("[ERROR] 로또 번호는 중복된 숫자를 포함하면 안됩니다."),
    OUT_OF_RANGE_MIN_NUMBER("[ERROR] 로또 번호는 1 미만이면 안됩니다."),
    OUT_OF_RANGE_MAX_NUMBER("[ERROR] 로또 번호는 45 초과이면 안됩니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

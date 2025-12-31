package lotto.exception;

public enum ErrorMessage {
    INVALID("유효하지 않은 입력입니다."),
    INVALID_INPUT("숫자를 입력해주세요"),
    INVALID_NUMBER_RANGE("양의 정수를 입력해주세요"),
    INVALID_LOTTO_PRICE("로또 가격에 나누어 떨어지게 입력해주세요"),
    INVALID_LOTTO_NUMBER_COUNT("로또 번호는 6개여야 합니다"),
    INVALID_LOTTO_NUMBER_RANGE("로또 숫자는 1~45 사이여야 합니다"),
    DUPLICATED_NUMBER("로또 숫자가 중복입니다");

    private static final String ERROR_PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }
}

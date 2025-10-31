package lotto;

public enum ErrorMessage {
    INVALID_INPUT_FORMAT("[ERROR] 숫자를 입력해 주세요."),
    INVALID_MONEY("[ERROR] 로또 구입 금액은 1,000원으로 나누어떨어져야 합니다.")
    ;

    public final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}

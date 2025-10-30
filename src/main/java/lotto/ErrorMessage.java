package lotto;

public enum ErrorMessage {
    INVALID_INPUT_MONEY("[ERROR] 로또 구입 금액은 1,000원으로 나누어떨어져야 합니다.")
    ;

    public final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}

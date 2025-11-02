package lotto.message;

public enum ErrorMessage {
    INVALID_INPUT_FORMAT("[ERROR] 숫자를 입력해 주세요."),
    INVALID_MONEY("[ERROR] 로또 구입 금액은 1,000원으로 나누어떨어져야 합니다."),
    DUPLICATE_NUMBER("[ERROR] 당첨 번호는 중복될 수 없습니다."),
    NUMBER_OUT_OF_SIZE("[ERROR] 당첨 번호는 6개여야 합니다."),
    DUPLICATE_BONUS_NUMBER("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다."),
    NUMBER_OUT_OF_RANGE("[ERROR] 로또 번호는 1부터 45까지의 숫자만 가능합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}

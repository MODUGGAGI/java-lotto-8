package lotto.message;

public enum InputMessage {
    GET_MONEY("구입금액을 입력해 주세요."),
    GET_WINNING_LOTTO("\n당첨 번호를 입력해 주세요."),
    GET_BONUS_NUMBER("\n보너스 번호를 입력해 주세요.");

    private final String message;

    InputMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}

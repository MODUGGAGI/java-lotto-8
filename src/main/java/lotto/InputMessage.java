package lotto;

public enum InputMessage {
    GET_MONEY("구입금액을 입력해 주세요."),
    ;

    public final String message;

    InputMessage(String message) {
        this.message = message;
    }
}

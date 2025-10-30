package lotto;

public enum OutputMessage {
    LOTTO_QUANTITY("%d개를 구매했습니다."),
    ;

    public final String message;

    OutputMessage(String message) {
        this.message = message;
    }
}

package lotto.message;

public enum OutputMessage {
    LOTTO_QUANTITY("%d개를 구매했습니다."),
    LOTTO_STATISTICS("\n당첨 통계\n" + "---"),
    LOTTO_YIELDS("총 수익률은 %.1f%%입니다.");

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}

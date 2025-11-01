package lotto.domain;

import java.util.Optional;

public enum Rank {
    FIFTH(3, false, 5_000, "3개 일치 (5,000원) - %d개"),
    FOURTH(4, false, 50_000, "4개 일치 (50,000원) - %d개"),
    THIRD(5, false, 1_500_000, "5개 일치 (1,500,000원) - %d개"),
    SECOND(5, true, 30_000_000, "5개 일치, 보너스 볼 일치 (30,000,000원) - %d개"),
    FIRST(6, false, 2_000_000_000, "6개 일치 (2,000,000,000원) - %d개");

    private final int matchCount;
    private final boolean bonusMatch;
    private final int prize;
    private final String message;

    Rank(int matchCount, boolean bonusMatch, int prize, String message) {
        this.matchCount = matchCount;
        this.bonusMatch = bonusMatch;
        this.prize = prize;
        this.message = message;
    }

    public static Optional<Rank> determineRank(int matchCount, boolean bonusMatch) {
        if (matchCount == 6) {
            return Optional.of(FIRST);
        }
        if (matchCount == 5 && bonusMatch) {
            return Optional.of(SECOND);
        }
        if (matchCount == 5) {
            return Optional.of(THIRD);
        }
        if (matchCount == 4) {
            return Optional.of(FOURTH);
        }
        if (matchCount == 3) {
            return Optional.of(FIFTH);
        }
        return Optional.empty();
    }

    public int calculatePrize(int count) {
        return this.prize * count;
    }

    @Override
    public String toString() {
        return message;
    }
}

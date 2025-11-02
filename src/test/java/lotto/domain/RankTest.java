package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class RankTest {

    @DisplayName("1등 판정 테스트")
    @Test
    void 등수_판정_1등() {
        Optional<Rank> rank = Rank.determineRank(6, false);

        assertThat(rank).contains(Rank.FIRST);
    }

    @DisplayName("5개 일치하고 보너스 번호 일치하는 경우 2등 판정 테스트")
    @Test
    void 등수_판정_2등() {
        Optional<Rank> rank = Rank.determineRank(5, true);

        assertThat(rank).contains(Rank.SECOND);
    }

    @DisplayName("5개 일치하고 보너스 번호 일치하지 않는 경우 3등 판정 테스트")
    @Test
    void 등수_판정_3등() {
        Optional<Rank> rank = Rank.determineRank(5, false);

        assertThat(rank).contains(Rank.THIRD);
    }

    @DisplayName("4등 판정 테스트")
    @Test
    void 등수_판정_4등() {
        Optional<Rank> rank = Rank.determineRank(4, false);

        assertThat(rank).contains(Rank.FOURTH);
    }

    @DisplayName("5등 판정 테스트")
    @Test
    void 등수_판정_5등() {
        Optional<Rank> rank = Rank.determineRank(3, false);

        assertThat(rank).contains(Rank.FIFTH);
    }

    @DisplayName("꽝 판정 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void 등수_판정_꽝(int matchCount) {
        Optional<Rank> rank = Rank.determineRank(matchCount, false);

        assertThat(rank).isEmpty();
    }

    @DisplayName("당첨 금액 계산")
    @Test
    void 상금_계산() {
        int prize = Rank.FIRST.calculatePrize(1);
        assertThat(prize).isEqualTo(2_000_000_000);
    }

}
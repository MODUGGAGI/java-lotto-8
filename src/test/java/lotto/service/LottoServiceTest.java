package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoServiceTest {

    private final LottoService lottoService = new LottoService();

    @DisplayName("구입 금액으로 로또 개수 확인하기")
    @Test
    void 구입금액_로또개수_확인() {
        int quantity = lottoService.calculateLottoQuantity(5000);

        assertThat(quantity).isEqualTo(5);
    }

    @DisplayName("주어진 개수 만큼 로또 생성")
    @Test
    void 로또_생성() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    List<Lotto> lottoList = lottoService.generateLottos(3);

                    assertThat(lottoList.size()).isEqualTo(3);
                },
                List.of(1, 2, 3, 4, 5, 6),
                List.of(7, 8, 9, 10, 11, 12),
                List.of(13, 14, 15, 16, 17, 18)
        );
    }

    @DisplayName("당첨 결과 계산 테스트")
    @Test
    void 당첨_결과_테스트() {
        // given
        List<Lotto> lottoList = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 7))
        );
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        // when
        Map<Rank, Integer> result = lottoService.calculateResult(lottoList, winningLotto, bonusNumber);

        // then
        assertThat(result.get(Rank.FIRST)).isEqualTo(1);
        assertThat(result.get(Rank.SECOND)).isEqualTo(1);
        assertThat(result.get(Rank.THIRD)).isEqualTo(0);
    }

    @DisplayName("수익률 계산 테스트")
    @Test
    void 수익률_계산_테스트() {
        //given
        Map<Rank, Integer> result = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }
        result.put(Rank.FIFTH, 1);

        //when
        double yields = lottoService.calculateYields(8000, result);

        //then
        assertThat(yields).isEqualTo(62.5);
    }

}
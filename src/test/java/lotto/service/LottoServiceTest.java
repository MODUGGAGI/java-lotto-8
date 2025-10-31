package lotto.service;

import lotto.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoServiceTest {

    private final LottoService lottoService = new LottoService();

    @Test
    @DisplayName("숫자가 아닌 문자가 섞여 들어왔을 경우")
    void 숫자X_입력값() {
        assertThatThrownBy(() -> lottoService.validateMoney("1000j"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 숫자를 입력해 주세요.");
    }

    @Test
    @DisplayName("1,000원으로 나누어떨어지지 않는 숫자가 들어온 경우")
    void 나누어_떨어지지_X_숫자_입력() {
        assertThatThrownBy(() -> lottoService.validateMoney("1500"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 구입 금액은 1,000원으로 나누어떨어져야 합니다.");
    }

    @Test
    @DisplayName("랜덤으로 생성된 로또 번호가 정렬")
    void 로또_번호_정렬() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    List<Lotto> lottoList = lottoService.generateLottos(1);
                    Lotto lotto = lottoList.getFirst();

                    assertThat(lotto.toString()).isEqualTo("[1, 2, 3, 4, 5, 6]");
                },
                List.of(5, 3, 1, 6, 2, 4)
        );
    }

    @Test
    @DisplayName("주어진 개수 만큼 로또 생성")
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

}
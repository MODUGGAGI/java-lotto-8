package lotto.validator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoValidatorTest {

    @DisplayName("숫자가 아닌 문자가 섞여 들어왔을 경우 테스트")
    @Test
    void 숫자X_입력값() {
        assertThatThrownBy(() -> LottoValidator.parseMoney("1000j"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 숫자를 입력해 주세요.");
    }

    @DisplayName("1,000원으로 나누어떨어지지 않는 숫자가 들어온 경우 테스트")
    @Test
    void 나누어_떨어지지_X_숫자_입력() {
        assertThatThrownBy(() -> LottoValidator.parseMoney("1500"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 구입 금액은 1,000원으로 나누어떨어져야 합니다.");
    }

    @DisplayName("당청 번호 숫자로 변환 테스트")
    @Test
    void 당첨_번호_파싱_테스트() {
        List<Integer> numbers = LottoValidator.parseWinningNumbers("1,2,3,4,5,6");

        Assertions.assertThat(numbers).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("당첨 번호에 숫자가 아닌 문자가 포함된 경우 테스트")
    @Test
    void 당첨_번호_숫자가_아닌_문자_포함() {
        assertThatThrownBy(() -> LottoValidator.parseWinningNumbers("a,1,2,3,4,5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 숫자를 입력해 주세요.");
    }
}
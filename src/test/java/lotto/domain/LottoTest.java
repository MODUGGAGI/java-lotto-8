package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 1~45 사이의 숫자가 아니면 예외가 발생한다.")
    @Test
    void 로또_번호가_주어진_범위가_아닐_경우_예외_발생() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("정렬되지 않은 리스트가 들어와도 자동 정렬된다.")
    @Test
    void 자동_정렬() {
        Lotto lotto = new Lotto(List.of(5, 1, 9, 33, 2, 4));

        assertThat(lotto.toString()).isEqualTo("[1, 2, 4, 5, 9, 33]");
    }

    @DisplayName("보너스 번호는 당첨 번호와 중복될 수 없다.")
    @Test
    void 보너스_반호_중복_불가() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThatThrownBy(() -> lotto.validateBonusNumber(1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당청 번호와 몇개가 일치하는지 확인")
    @Test
    void 당청_개수_확인() {
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));

        int matchCount = lotto.countMatchingNumbers(winningLotto);

        assertThat(matchCount).isEqualTo(5);
    }
}

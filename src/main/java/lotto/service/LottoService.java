package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.ErrorMessage;
import lotto.Lotto;

public class LottoService {

    public int calculateLottoQuantity(int money) {
        return money / 1000;
    }

    public int validateMoney(String rawMoney) {
        int money = parseToInt(rawMoney);
        validateMoneyBy1000(money);
        return money;
    }

    public List<Lotto> generateLottos(int quantity) {
        List<Lotto> lottoList = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            List<Integer> integers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            Collections.sort(integers);

            Lotto lotto = new Lotto(integers);
            lottoList.add(lotto);
        }

        return lottoList;
    }

    private int parseToInt(String rawMoney) {
        try {
            return Integer.parseInt(rawMoney);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_FORMAT.message);
        }
    }

    private void validateMoneyBy1000(int money) {
        if (money % 1000 != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MONEY.message);
        }
    }
}

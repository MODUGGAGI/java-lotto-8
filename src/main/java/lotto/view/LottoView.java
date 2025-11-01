package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.message.InputMessage;
import lotto.message.OutputMessage;

public class LottoView {

    public String getMoney() {
        System.out.println(InputMessage.GET_MONEY);
        return Console.readLine();
    }

    public String getWinningLotto() {
        System.out.println(InputMessage.GET_WINNING_LOTTO);
        return Console.readLine();
    }

    public String getBonusNumber() {
        System.out.println(InputMessage.GET_BONUS_NUMBER);
        return Console.readLine();
    }

    public void printLottoNumbers(int quantity, List<Lotto> lottoList) {
        System.out.println();
        System.out.printf(OutputMessage.LOTTO_QUANTITY + "\n", quantity);

        for (Lotto lotto : lottoList) {
            System.out.println(lotto.toString());
        }
    }

    public void printResult(Map<Rank, Integer> result) {
        System.out.println(OutputMessage.LOTTO_STATISTICS);
        for (Rank rank : result.keySet()) {
            System.out.printf(rank.toString() + "\n", result.get(rank));
        }
    }

    public void printYields(double yields) {
        System.out.printf(OutputMessage.LOTTO_YIELDS + "\n", yields);
    }

    public void printError(String errorMessage) {
        System.out.println("\n" + errorMessage);
    }
}

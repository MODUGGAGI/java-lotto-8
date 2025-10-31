package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import lotto.InputMessage;
import lotto.Lotto;
import lotto.OutputMessage;

public class LottoView {

    public String getMoney() {
        System.out.println(InputMessage.GET_MONEY.message);
        return Console.readLine();
    }

    public void printLottoNumbers(int quantity, List<Lotto> lottoList) {
        System.out.println();
        System.out.printf((OutputMessage.LOTTO_QUANTITY.message) + "%n", quantity);

        for (Lotto lotto : lottoList) {
            System.out.println(lotto.toString());
        }
    }

    public void printError(String errorMessage) {
        System.out.println("\n" + errorMessage + "\n");
    }
}

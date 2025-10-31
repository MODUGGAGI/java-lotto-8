package lotto.controller;

import java.util.List;
import lotto.Lotto;
import lotto.service.LottoService;
import lotto.view.LottoView;

public class LottoController {

    private final LottoView view = new LottoView();
    private final LottoService service = new LottoService();

    public void startLotto() {
        int money = getMoney();
        int quantity = service.calculateLottoQuantity(money);
        List<Lotto> lottoList = service.generateLottos(quantity);
        view.printLottoNumbers(quantity, lottoList);
    }

    private int getMoney() {
        try {
            String rawMoney = view.getMoney();
            return service.validateMoney(rawMoney);
        } catch (IllegalArgumentException e) {
            view.printError(e.getMessage());
            return getMoney();
        }
    }
}

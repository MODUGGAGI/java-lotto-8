package lotto.controller;

import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.service.LottoService;
import lotto.validator.LottoValidator;
import lotto.view.LottoView;

public class LottoController {

    private final LottoView view = new LottoView();
    private final LottoService service = new LottoService();

    public void startLotto() {
        int money = getMoney();
        int quantity = service.calculateLottoQuantity(money);
        List<Lotto> lottoList = service.generateLottos(quantity);
        view.printLottoNumbers(quantity, lottoList);

        Lotto winningLotto = getWinningLotto();
        int bonusNumber = getBonusNumber(winningLotto);
        Map<Rank, Integer> result = service.calculateResult(lottoList, winningLotto, bonusNumber);
        double yields = service.calculateYields(money, result);

        view.printResult(result);
        view.printYields(yields);
    }

    private int getBonusNumber(Lotto winningLotto) {
        try {
            String rawNumber = view.getBonusNumber();
            int bonusNumber = LottoValidator.parseBonusNumber(rawNumber);
            winningLotto.validateBonusNumber(bonusNumber);
            return bonusNumber;
        } catch (IllegalArgumentException e) {
            view.printError(e.getMessage());
            return getBonusNumber(winningLotto);
        }
    }

    private Lotto getWinningLotto() {
        try {
            String rawNumbers = view.getWinningLotto();
            List<Integer> numbers = LottoValidator.parseWinningNumbers(rawNumbers);
            return service.generateWinningLotto(numbers);
        } catch (IllegalArgumentException e) {
            view.printError(e.getMessage());
            return getWinningLotto();
        }
    }

    private int getMoney() {
        try {
            String rawMoney = view.getMoney();
            return LottoValidator.parseMoney(rawMoney);
        } catch (IllegalArgumentException e) {
            view.printError(e.getMessage());
            return getMoney();
        }
    }
}

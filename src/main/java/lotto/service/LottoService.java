package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.Rank;

public class LottoService {

    public int calculateLottoQuantity(int money) {
        return money / 1000;
    }

    public List<Lotto> generateLottos(int quantity) {
        List<Lotto> lottoList = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            lottoList.add(new Lotto(numbers));
        }

        return lottoList;
    }

    public Lotto generateWinningLotto(List<Integer> numbers) {
        return new Lotto(numbers);
    }

    public Map<Rank, Integer> calculateResult(List<Lotto> lottoList, Lotto winningLotto, int bonusNumber) {
        Map<Rank, Integer> result = createResultMap();

        for (Lotto lotto : lottoList) {
            int matchCount = lotto.countMatchingNumbers(winningLotto);
            Rank.determineRank(matchCount, lotto.containsNumber(bonusNumber))
                    .ifPresent(rank -> result.put(rank, result.get(rank) + 1));
        }

        return result;
    }

    public double calculateYields(int money, Map<Rank, Integer> result) {
        int totalPrize = calculateTotalPrize(result);
        return (double) totalPrize / money * 100;
    }

    private Map<Rank, Integer> createResultMap() {
        Map<Rank, Integer> result = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }
        return result;
    }

    private int calculateTotalPrize(Map<Rank, Integer> result) {
        return result.keySet().stream()
                .mapToInt(rank -> rank.calculatePrize(result.get(rank)))
                .sum();
    }
}

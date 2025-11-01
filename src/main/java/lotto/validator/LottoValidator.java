package lotto.validator;

import java.util.Arrays;
import java.util.List;
import lotto.message.ErrorMessage;

public class LottoValidator {

    public static int parseMoney(String rawMoney) {
        int money = parseToInt(rawMoney);
        validateMoney(money);
        return money;
    }

    public static List<Integer> parseWinningNumbers(String rawNumbers) {
        try {
            return Arrays.stream(rawNumbers.split(","))
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_FORMAT.toString());
        }
    }

    public static int parseBonusNumber(String rawNumber) {
        return parseToInt(rawNumber);
    }

    private static int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_FORMAT.toString());
        }
    }

    private static void validateMoney(int money) {
        if (money % 1000 != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MONEY.toString());
        }
    }
}
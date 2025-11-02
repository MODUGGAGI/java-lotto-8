package lotto.domain;

import java.util.List;
import lotto.message.ErrorMessage;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = sortNumbers(numbers);
    }

    private void validate(List<Integer> numbers) {
        boolean invalidRange = numbers.stream().anyMatch(number -> number < 1 || 45 < number);
        if (invalidRange) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_OUT_OF_RANGE.toString());
        }

        int originalSize = numbers.size();
        if (originalSize != 6) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_OUT_OF_SIZE.toString());
        }

        int uniqueCount = (int) numbers.stream().distinct().count();
        if (originalSize != uniqueCount) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_NUMBER.toString());
        }
    }

    private List<Integer> sortNumbers(List<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .toList();
    }

    public int countMatchingNumbers(Lotto other) {
        return (int) this.numbers.stream()
                .filter(other.numbers::contains)
                .count();
    }

    public boolean containsNumber(int bonusNumber) {
        return this.numbers.contains(bonusNumber);
    }

    public void validateBonusNumber(int bonusNumber) {
        if (this.numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_BONUS_NUMBER.toString());
        }
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}

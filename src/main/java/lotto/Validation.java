package lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Validation {
    private final static int ONE_LOTTO = 1000;
    private final static int LOTTO_LENGTH = 6;

    private final static int START_LOTTO_NUMBER = 1;
    private final static int END_LOTTO_NUMBER = 45;
    private final static String ERROR_MESSAGE = "[ERROR] ";
    private final static String INVALID_MONEY_MESSAGE = "구입 금액은 " + ONE_LOTTO + "원 단위로만 입력 가능합니다.";
    private final static String INVALID_TYPE_MESSAGE = "숫자만 입력 가능합니다.";
    private final static String INVALID_LENGTH_MESSAGE = LOTTO_LENGTH + "개의 숫자를 입력해 주세요.";
    private final static String DUPLICATE_MESSAGE = "중복되지 않는 숫자를 입력해 주세요.";
    private final static String INVALID_NUMBER_RANGE = "로또 번호는 " + START_LOTTO_NUMBER + "부터 " + END_LOTTO_NUMBER + " 사이의 숫자여야 합니다.";


    public static void isNumberType(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_MESSAGE + INVALID_TYPE_MESSAGE);
        }
    }

    public static void isDividedByThousand(int value) {
        if (value == 0 || value % ONE_LOTTO != 0) {
            System.out.println(ERROR_MESSAGE + INVALID_MONEY_MESSAGE);
            throw new IllegalArgumentException(ERROR_MESSAGE + INVALID_MONEY_MESSAGE);
        }
    }

    public static void isNumberList(List<String> inputList) {
        for (String value : inputList) {
            isNumberType(value);
        }
    }

    public static void isValidLength(List<String> prizeNumberList) {
        if (prizeNumberList.size() != LOTTO_LENGTH) {
            throw new IllegalArgumentException(ERROR_MESSAGE + INVALID_LENGTH_MESSAGE);
        }
    }

    public static void isContainDuplicateNumber(List<String> valueList) {
        Set<String> listSet = new HashSet<>(valueList);
        if (listSet.size() != LOTTO_LENGTH) {
            throw new IllegalArgumentException(ERROR_MESSAGE + DUPLICATE_MESSAGE);
        }
    }

    public static void isValidNumberRange(List<String> valueList) {
        int number;
        for (int i = 0; i < LOTTO_LENGTH; i++) {
            number = Integer.parseInt(valueList.get(i));
            if (number < START_LOTTO_NUMBER || number > END_LOTTO_NUMBER) {
                throw new IllegalArgumentException(ERROR_MESSAGE + INVALID_NUMBER_RANGE);
            }
        }
    }

    public static void validateMoneyInput(String moneyInput) {
        isNumberType(moneyInput);
        int money = Integer.parseInt(moneyInput);
        isDividedByThousand(money);
    }

    public static void validatePrizeNumberInput(List<String> prizeNumberInput) {
        isNumberList(prizeNumberInput);
        isValidLength(prizeNumberInput);
        isValidNumberRange(prizeNumberInput);
        isContainDuplicateNumber(prizeNumberInput);
    }
}
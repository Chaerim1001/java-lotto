package lotto;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;

public class UI {
    private static final String MONEY_INPUT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String PURCHASE_LOTTO_MESSAGE = "개를 구매했습니다.";
    private static final String PRIZE_INPUT_MESSAGE = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_INPUT_MESSAGE = "보너스 번호를 입력해 주세요.";
    private final LottoLogic lottoLogic;

    public UI() {
        lottoLogic = new LottoLogic(moneyInput());
        printNumberOfLotto();
        printLottoNumber();

        List<String> prizeNumbers = prizeNumbersInput();
        setPrizeNumbers(prizeNumbers);
        String bonusNumber = bonusNumberInput();
        setBonusNumber(bonusNumber);

        printLottoResult();
    }

    private int moneyInput() {
        System.out.println(MONEY_INPUT_MESSAGE);
        String moneyInput = Console.readLine();
        Validation.validateMoneyInput(moneyInput);

        return Integer.parseInt(moneyInput);
    }

    private void printNumberOfLotto() {
        System.out.println(this.lottoLogic.getNumberOfLotto() + PURCHASE_LOTTO_MESSAGE);
    }

    private void printLottoNumber() {
        for (int i = 0; i < this.lottoLogic.getBuyLottoList().size(); i++) {
            System.out.println(this.lottoLogic.getBuyLottoList().get(i).getNumbers());
        }
    }

    private List<String> prizeNumbersInput() {
        System.out.println(PRIZE_INPUT_MESSAGE);
        String prizeNumberInput = Console.readLine();
        return sliceInputNumber(prizeNumberInput);
    }

    private void setPrizeNumbers(List<String> prizeNumbers) {
        Validation.validatePrizeNumberInput(prizeNumbers);
        this.lottoLogic.setPrizeNumbers(stringListToIntegerList(prizeNumbers));
    }

    private String bonusNumberInput() {
        System.out.println(BONUS_INPUT_MESSAGE);
        String bonusInput = Console.readLine();
        return bonusInput;
    }

    private void setBonusNumber(String bonusInput) {
        Validation.validateBonusInput(bonusInput, this.lottoLogic.getPrizeNumbers());
        this.lottoLogic.setBonusNumber(Integer.parseInt(bonusInput));
    }

    public List<String> sliceInputNumber(String value) {
        return List.of(value.split(","));
    }

    public List<Integer> stringListToIntegerList(List<String> stringList) {
        List<Integer> integerList = new ArrayList<>();
        for (String stringValue : stringList) {
            integerList.add(Integer.parseInt(stringValue));
        }
        return integerList;
    }

    public void printLottoResult() {
        this.lottoLogic.calculateResult();
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.println("3개 일치 (5,000원) - " + this.lottoLogic.getThreeHit() + "개");
        System.out.println("4개 일치 (50,000원) - " + this.lottoLogic.getFourHit() + "개");
        System.out.println("5개 일치 (1,500,000원) - " + this.lottoLogic.getFiveHit() + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + this.lottoLogic.getFiveHitAndBonus() + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + this.lottoLogic.getSixHit() + "개");
        System.out.println("총 수익률은 " + this.lottoLogic.getEarningRate() + "%입니다.");
    }
}
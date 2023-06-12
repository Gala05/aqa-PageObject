package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private final SelenideElement amount = $("[data-test-id='amount'] .input__control");
    private final SelenideElement fromCard = $("[data-test-id='from'] .input__control");
    private final SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private final SelenideElement transferHead = $(byText("Пополнение карты"));
    private final SelenideElement errorMessage = $("[data-test-id='error-message']");

    public TransferPage() {
        transferHead.shouldBe(visible);
    }

    public DashboardPage makeValidTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        makeTransfer(amountToTransfer, cardInfo);
        return new DashboardPage();
    }

    public void makeTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        amount.setValue(amountToTransfer);
        fromCard.setValue(cardInfo.getCardNumber());
        transferButton.click();
    }

    public void findErrorMessage(String expectedText) {
        errorMessage.shouldHave(exactText(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }

//    public DashboardPage replenishInFirst() {
//        replenishMoneyInFirstCard.click();
//        amount.setValue(DataHelper.generateValidAmount(10000));
//        fromCard.setValue(DataHelper.CardInfo.);
//        transferButton.click();
//        return new DashboardPage();
//    }
//
//    public DashboardPage replenishInSecond() {
//        replenishMoneyInSecondCard.click();
//        amount.setValue("600");
//        fromCard.setValue(DataHelper.getFirstCard());
//        transferButton.click();
//        return new DashboardPage();
//    }
}
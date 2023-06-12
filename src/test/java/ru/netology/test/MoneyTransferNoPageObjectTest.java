package ru.netology.test;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MoneyTransferNoPageObjectTest {
    @Test
    void test() {
        open("http://localhost:9999");
        $("[data-test-id=login] input").setValue("vasya");
        $("[data-test-id=password] input").setValue("qwerty123");
        $("[data-test-id=action-login]").click();
        $("[data-test-id=code] input").setValue("12345");
        $("[data-test-id=action-verify]").click();

        $$("[data-test-id='action-deposit']").first().click();
        $("[data-test-id='amount'] .input__control").setValue("500");
        $("[data-test-id='from'] .input__control").setValue("5559 0000 0000 0002");
        $("[data-test-id='action-transfer']").click();
        $x("//h1[contains(text(), 'Ваши карты')]").shouldBe(visible);
    }
}

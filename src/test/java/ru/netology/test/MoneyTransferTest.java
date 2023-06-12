package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;

class MoneyTransferTest {

    DashboardPage dashboardPage;

    @BeforeEach
    void setup() {
        LoginPage loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = getVerificationCodeFor(authInfo);
        dashboardPage = verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsFromFirstCard() {
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        var firstCardBalance = dashboardPage.getCardBalance(firstCardInfo);
        var secondCardBalance = dashboardPage.getCardBalance(secondCardInfo);
        var amount = generateValidAmount(firstCardBalance);
        var expectedBalanceFirstCard = firstCardBalance - amount;
        var expectedBalanceSecondCard = secondCardBalance + amount;
        var transferPage = dashboardPage.selectCardToTransfer(secondCardInfo);
        dashboardPage = transferPage.makeValidTransfer(String.valueOf(amount), firstCardInfo);
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsFromSecondCard() {
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        var firstCardBalance = dashboardPage.getCardBalance(firstCardInfo);
        var secondCardBalance = dashboardPage.getCardBalance(secondCardInfo);
        var amount = generateValidAmount(secondCardBalance);
        var expectedBalanceFirstCard = firstCardBalance + amount;
        var expectedBalanceSecondCard = secondCardBalance - amount;
        var transferPage = dashboardPage.selectCardToTransfer(firstCardInfo);
        dashboardPage = transferPage.makeValidTransfer(String.valueOf(amount), secondCardInfo);
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }

//    @Test
//    void shouldTransferMoneyBetweenOwnCardsFromSecondCard() {
//        open("http://localhost:9999");
//        var loginPage = new LoginPage(); //создаем экземпляр странички LoginPage
////    var loginPage = open("http://localhost:9999", LoginPageV1.class);
//        var authInfo = DataHelper.getAuthInfo();//готовим данные для тестов, используя DataHelper проверяем успешный вход
//        // метод getAuthInfo, он нам возвращает объект с данными и мы их передаем далее в validLogin
//        var verificationPage = loginPage.validLogin(authInfo);
//        var verificationCode = DataHelper.getVerificationCodeFor(authInfo); //получаем VerificationCode
//        verificationPage.validVerify(verificationCode); //на verificationPage вводим код
//        var transferPage = new TransferPage();
//        transferPage.replenishInFirst();
//    }
//
//    @Test
//    void shouldTransferMoneyBetweenOwnCardsFromFirstCard() {
//        open("http://localhost:9999");
//        var loginPage = new LoginPage();
//        var authInfo = DataHelper.getAuthInfo();
//        var verificationPage = loginPage.validLogin(authInfo);
//        var verificationCode = DataHelper.getVerificationCodeFor(authInfo); //получаем VerificationCode
//        verificationPage.validVerify(verificationCode); //на verificationPage вводим код
//        var transferPage = new TransferPage();
//        transferPage.replenishInSecond();
//    }
//
//    @Test
//    void shouldTransferMoneyBetweenOwnCardsV2() {
//        open("http://localhost:9999");
//        var loginPage = new LoginPage(); //здесь мы инициализируем через конструктор
////    var loginPage = open("http://localhost:9999", LoginPageV2.class);
//        var authInfo = DataHelper.getAuthInfo();
//        var verificationPage = loginPage.validLogin(authInfo);
//        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
//        verificationPage.validVerify(verificationCode);
//    }
}
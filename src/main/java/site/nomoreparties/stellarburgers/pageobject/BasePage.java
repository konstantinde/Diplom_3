package site.nomoreparties.stellarburgers.pageobject;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class BasePage {
    /**
     * Класс для для базовых элементов, которые присутствуют на всех страницах, хедеры, футеры, банеры...
     */

    public String getCurrentUrl(){
        // получаем текущий УРЛ, что в адресной строке
        return WebDriverRunner.getWebDriver().getCurrentUrl();
    }

    // Кнопка Личный Кабинет из плашки хедера
    @FindBy(how = How.XPATH, using = "//a[@href='/account']")
    private SelenideElement buttonPersonalArea;

    // Кнопка Конструкток из плашки хедера
    @FindBy(how = How.XPATH, using = "//header/nav/ul/li/a[@href='/']")
    private SelenideElement buttonConstructor;

    // Кнопка Лента Заказов из плашки хедера
    @FindBy(how = How.XPATH, using = "//a[@href='/feed']")
    private SelenideElement buttonOrderFeed;

    // Баннер из плашки хедера
    @FindBy(how = How.XPATH, using = "//header/nav/div/a[@href='/']")
    private SelenideElement headerBanner;

    @Step("Нажали на кнопку ЛК до логина")
    public LoginPage clickPersonalAreaButtonBeforeLogin() {
        buttonPersonalArea.click();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.waitForLoadLoginPage();
        return loginPage;
    }

    @Step("Нажали на кнопку Конструктор")
    public void clickConstructorButton() {
        buttonConstructor.click();
    }

    @Step("Нажали на баннер")
    public void clickHeaderBanner() {
        headerBanner.click();
    }

    @Step("Нажали на кнопку Лента заказов")
    public FeedPage clickOrderFeedButton() {
        buttonOrderFeed.click();
        FeedPage feedPage = page(FeedPage.class);
        feedPage.waitForFeedPage();
        return feedPage;
    }
    @Step("Нажали на кнопку ЛК после логина")
    public AccountProfilePage clickPersonalAreaButtonAfterLogin() {
        buttonPersonalArea.click();
        AccountProfilePage accountProfilePage = page(AccountProfilePage.class);
        accountProfilePage.waitForAccountProfilePage();
        return accountProfilePage;
    }


}

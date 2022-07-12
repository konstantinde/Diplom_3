package site.nomoreparties.stellarburgers.pageobject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class HomePage extends BasePage {

    public static final String URL = "https://stellarburgers.nomoreparties.site/";

    // Кнопка Войти в аккаунт (отображается до логина)
    @FindBy(how = How.XPATH, using = "//button[text()='Войти в аккаунт']")
    private SelenideElement buttonEnterInAccount;

    // Кнопка Оформить заказ (отображается после логина)
    @FindBy(how = How.XPATH, using = "//button[text()='Оформить заказ']")
    private SelenideElement buttonCreateOrder;

    // Заголовок Соберте бургер
    @FindBy(how = How.XPATH, using = "//h1[text()='Соберите бургер']")
    private SelenideElement textCollectBurger;

    // Кнопка Булки во фрейме
    @FindBy(how = How.XPATH, using = "//div/span[text()='Булки']")
    private SelenideElement frameButtonBun;

    // Кнопка Соусы во фрейме
    @FindBy(how = How.XPATH, using = "//div/span[text()='Соусы']")
    private SelenideElement frameButtonSauce;

    // Кнопка Начинки во фрейме
    @FindBy(how = How.XPATH, using = "//div/span[text()='Начинки']")
    private SelenideElement frameButtonFilling;

    // Подзаголовок Булки во фрейме
    @FindBy(how = How.XPATH, using = "//h2[text()='Булки']")
    private SelenideElement frameTextBun;

    // Подзаголовок Соусы во фрейме
    @FindBy(how = How.XPATH, using = "//h2[text()='Соусы']")
    private SelenideElement frameTextSauce;

    // Подзаголовок Начинки во фрейме
    @FindBy(how = How.XPATH, using = "//h2[text()='Начинки']")
    private SelenideElement frameTextFilling;

    public boolean isVisibleEnterInAccountButton() {
        return buttonEnterInAccount.shouldBe(visible).isDisplayed();
    }

    public boolean isVisibleCreateOrderButton() {
        return buttonCreateOrder.shouldBe(visible).isDisplayed();
    }

    public void waitForLoadHomePageAfterLogin() {
        buttonCreateOrder.shouldBe(visible);
    }

    public void waitForLoadHomePage() {
        buttonEnterInAccount.shouldBe(visible);
    }

    public LoginPage clickEnterInAccountButton() {
        buttonEnterInAccount.click();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.waitForLoadLoginPage();
        return loginPage;
    }

    public boolean isVisibleCollectBurgerText() {
        return textCollectBurger.shouldBe(visible).isDisplayed();
    }

    public void clickFrameButtonBun() {
        frameButtonBun.click();
    }

    public void clickFrameButtonSauce() {
        frameButtonSauce.click();
    }

    public void clickFrameButtonFilling() {
        frameButtonFilling.click();
    }

    public boolean isVisibleFrameTextBun() {
        return frameTextBun.hover().is(visible);
    }

    public boolean isVisibleFrameTextSauce() {
        return frameTextSauce.hover().is(visible);
    }

    public boolean isVisibleFrameTextFilling() {
        return frameTextFilling.hover().is(visible);
    }

}

package site.nomoreparties.stellarburgers.pageobject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class HomePage extends BasePage {

    public static final String URL = "https://stellarburgers.nomoreparties.site/";

    @FindBy(how = How.XPATH, using = "//button[text()='Войти в аккаунт']")
    private SelenideElement buttonEnterInAccount;

    @FindBy(how = How.XPATH, using = "//button[text()='Оформить заказ']")
    private SelenideElement buttonCreateOrder;

    @FindBy(how = How.XPATH, using = "//h1[text()='Соберите бургер']")
    private SelenideElement textCollectBurger;


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


}

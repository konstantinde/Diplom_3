package site.nomoreparties.stellarburgers.pageobject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage extends BasePage {

    public static final String URL = "https://stellarburgers.nomoreparties.site/login";

    @FindBy(how = How.XPATH, using = "//button[text()='Войти']")
    private SelenideElement buttonEnter;

    @FindBy(how = How.XPATH, using = "//h2[text()='Вход']")
    private SelenideElement textHeaderEnter;

    @FindBy(how = How.XPATH, using = "//label[text()='Email']/following-sibling::input")
    private SelenideElement inputEmail;

    @FindBy(how = How.XPATH, using = "//label[text()='Пароль']/following-sibling::input")
    private SelenideElement inputPassword;

    @FindBy(how = How.LINK_TEXT, using = "Восстановить пароль")
    private SelenideElement linkRecoverPassword;


    public void setEmail(String email) {
        inputEmail.setValue(email);
    }

    public void setPassword(String password) {
        inputPassword.setValue(password);
    }

    public String getInputEmail() {
        return inputEmail.scrollTo().getText();
    }

    public HomePage clickLoginButton() {
        buttonEnter.click();
        HomePage homePage = page(HomePage.class);
        homePage.waitForLoadHomePageAfterLogin();
        return homePage;
    }


    public boolean isVisibleEnterHeader(){
        return textHeaderEnter.shouldBe(visible).isDisplayed();
    }

    public boolean isVisibleEnterButton(){
        return buttonEnter.shouldBe(visible).isDisplayed();
    }

    // метод ожидания загрузки страницы
    public void waitForLoadLoginPage(){
        linkRecoverPassword.shouldBe(visible);
    }

}

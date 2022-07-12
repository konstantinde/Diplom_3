package site.nomoreparties.stellarburgers.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class ForgotPassword {

    public static final String URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    // Кнопка Войти
    @FindBy(how = How.LINK_TEXT, using = "Войти")
    private SelenideElement linkButtonLogin;

    @Step("Нажали на ссылку Войти на странице Восстановления пароля")
    public LoginPage clickLinkButtonLogin() {
        linkButtonLogin.click();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.waitForLoadLoginPage();
        return loginPage;
    }
}

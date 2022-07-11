package site.nomoreparties.stellarburgers.pageobject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class ForgotPassword {

    public static final String URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    @FindBy(how = How.LINK_TEXT, using = "Войти")
    private SelenideElement linkButtonLogin;

    public LoginPage clickLinkButtonLogin() {
        linkButtonLogin.click();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.waitForLoadLoginPage();
        return loginPage;
    }
}

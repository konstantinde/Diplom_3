package site.nomoreparties.stellarburgers.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class RegisterPage extends BasePage{

    public static final String URL = "https://stellarburgers.nomoreparties.site/register";

    @FindBy(how = How.XPATH, using = "//button[text()='Зарегистрироваться']")
    private SelenideElement buttonRegistration;

    @FindBy(how = How.XPATH, using = "//label[text()='Имя']/following-sibling::input")
    private SelenideElement inputName;

    @FindBy(how = How.XPATH, using = "//label[text()='Email']/following-sibling::input")
    private SelenideElement inputEmail;

    @FindBy(how = How.XPATH, using = "//label[text()='Пароль']/following-sibling::input")
    private SelenideElement inputPassword;

    @FindBy(how = How.XPATH, using = "//p[text()='Некорректный пароль']")
    private SelenideElement textWrongPassword;

    public void setName(String name) {
        inputName.setValue(name);
    }

    public void setEmail(String email) {
        inputEmail.setValue(email);
    }

    public void setPassword(String password) {
        inputPassword.setValue(password);
    }

    public void clickRegistrationButton() {
        buttonRegistration.click();
    }

    public boolean isVisibleTextWrongPassword(){
        return textWrongPassword.shouldBe(visible).isDisplayed();
    }

    public LoginPage makeUserRegistration(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegistrationButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.waitForLoadLoginPage();
        return loginPage;
    }
}

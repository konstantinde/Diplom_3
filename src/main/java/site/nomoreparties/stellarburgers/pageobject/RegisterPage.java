package site.nomoreparties.stellarburgers.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class RegisterPage extends BasePage{

    public static final String URL = "https://stellarburgers.nomoreparties.site/register";

    // Ссылка Зарегистрироваться
    @FindBy(how = How.XPATH, using = "//button[text()='Зарегистрироваться']")
    private SelenideElement buttonRegistration;

    // Поле ввода имени
    @FindBy(how = How.XPATH, using = "//label[text()='Имя']/following-sibling::input")
    private SelenideElement inputName;

    // Поле ввода почты
    @FindBy(how = How.XPATH, using = "//label[text()='Email']/following-sibling::input")
    private SelenideElement inputEmail;

    // Поле ввода пароля
    @FindBy(how = How.XPATH, using = "//label[text()='Пароль']/following-sibling::input")
    private SelenideElement inputPassword;

    // Алерт Некорректный пароль
    @FindBy(how = How.XPATH, using = "//p[text()='Некорректный пароль']")
    private SelenideElement textWrongPassword;

    // Ссылка Войти
    @FindBy(how = How.LINK_TEXT, using = "Войти")
    private SelenideElement linkButtonLogin;

    @Step("Заполнили поле имя")
    public void setName(String name) {
        inputName.setValue(name);
    }

    @Step("Заполнили поле Email")
    public void setEmail(String email) {
        inputEmail.setValue(email);
    }

    @Step("Заполнили поле Пароль")
    public void setPassword(String password) {
        inputPassword.setValue(password);
    }

    @Step("Нажали на кнопку Регистрация")
    public void clickRegistrationButton() {
        buttonRegistration.click();
    }

    public boolean isVisibleTextWrongPassword(){
        return textWrongPassword.shouldBe(visible).isDisplayed();
    }

    @Step("Запонляем форму регистрации")
    public LoginPage makeUserRegistration(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegistrationButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.waitForLoadLoginPage();
        return loginPage;
    }

    @Step("Вводим почту/пароль и жмем Вход")
    public LoginPage clickLinkButtonLogin() {
        linkButtonLogin.click();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.waitForLoadLoginPage();
        return loginPage;
    }
}

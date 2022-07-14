package site.nomoreparties.stellarburgers.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage extends BasePage {

    public static final String URL = "https://stellarburgers.nomoreparties.site/login";

    // Кнопка Войти
    @FindBy(how = How.XPATH, using = "//button[text()='Войти']")
    private SelenideElement buttonEnter;

    // Подзаголовок Вход
    @FindBy(how = How.XPATH, using = "//h2[text()='Вход']")
    private SelenideElement textEnter;

    // Поле ввода почты
    @FindBy(how = How.XPATH, using = "//label[text()='Email']/following-sibling::input")
    private SelenideElement inputEmail;

    // Поле ввода пароля
    @FindBy(how = How.XPATH, using = "//label[text()='Пароль']/following-sibling::input")
    private SelenideElement inputPassword;

    // Ссылка Восстановить пароль
    @FindBy(how = How.LINK_TEXT, using = "Восстановить пароль")
    private SelenideElement linkRecoverPassword;

    @Step("Заполнили поле Email")
    public void setEmail(String email) {
        inputEmail.setValue(email);
    }

    @Step("Заполнили поле Пароль")
    public void setPassword(String password) {
        inputPassword.setValue(password);
    }

    @Step("Заполняем поля почты/пароля на странице логина")
    public HomePage makeUserLogin(String email, String password) {
        setEmail(email);
        setPassword(password);
        buttonEnter.click();
        HomePage homePage = page(HomePage.class);
        homePage.waitForLoadHomePageAfterLogin();
        return homePage;
    }

    public boolean isVisibleEnterText(){
        return textEnter.shouldBe(visible).isDisplayed();
    }

    public boolean isVisibleEnterButton(){
        return buttonEnter.shouldBe(visible).isDisplayed();
    }

    @Step("Загрузка страницы логина")
    public void waitForLoadLoginPage(){
        linkRecoverPassword.shouldBe(visible);
    }

}

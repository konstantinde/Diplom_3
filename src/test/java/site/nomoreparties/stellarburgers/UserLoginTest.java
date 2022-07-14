package site.nomoreparties.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.apiclients.UserClient;
import site.nomoreparties.stellarburgers.helpers.UserGenerator;
import site.nomoreparties.stellarburgers.models.users.User;
import site.nomoreparties.stellarburgers.pageobject.ForgotPassword;
import site.nomoreparties.stellarburgers.pageobject.HomePage;
import site.nomoreparties.stellarburgers.pageobject.LoginPage;
import site.nomoreparties.stellarburgers.pageobject.RegisterPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class UserLoginTest {

    SoftAssertions softAssertions = new SoftAssertions();
    UserClient userClient = new UserClient();
    User user;
    String bearerToken;

    @Before
    public void setUp() {
        // для запуска в Я.браузере
//        ChromeOptions options = new ChromeOptions();
//        System.setProperty("webdriver.chrome.driver", "C:\\chromeDriver\\chromedriver.exe");
//        options.setBinary("C:\\Users\\vorku\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
//        WebDriver driver = new ChromeDriver(options);
//        WebDriverRunner.setWebDriver(driver);

        // Создаем юзера для регистрации
        user = UserGenerator.getRandomUser();
        // Регистрируем юзера
        ValidatableResponse userRegister = userClient.register(user);
        // Сохраняем его токен
        bearerToken = userRegister.extract().body().path("accessToken");
    }

    @After
    public void tearDown() {
        closeWebDriver();
        // Удаляем созданного для теста пользователя
        userClient.delete(bearerToken);
    }

    @DisplayName("Логин по кнопке Войти в аккаунт на главной")
    @Description("Проверяем, что попадаем на страницу логина по кнопке Войти в аккаунт на главной странице и можно" +
            "успешно залогиниться")
    @Test
    public void shouldLoginByEnterInAccountButton() {
        HomePage homePage = open(HomePage.URL, HomePage.class);
        LoginPage loginPage = homePage.clickEnterInAccountButton();
        homePage = loginPage.makeUserLogin(user.getEmail(), user.getPassword());

        softAssertions.assertThat(homePage.isVisibleCreateOrderButton()).isTrue();
        softAssertions.assertAll();
    }

    @DisplayName("Логин через кнопку Личный кабинет")
    @Description("Проверяем, что попадаем на страницу логина через кнопку Личный кабинет в хедере и можно" +
            "успешно залогиниться")
    @Test
    public void shouldLoginByPersonalAccountButton() {
        HomePage homePage = open(HomePage.URL, HomePage.class);
        LoginPage loginPage = homePage.clickPersonalAreaButtonBeforeLogin();
        homePage = loginPage.makeUserLogin(user.getEmail(), user.getPassword());

        softAssertions.assertThat(homePage.isVisibleCreateOrderButton()).isTrue();
        softAssertions.assertAll();
    }

    @DisplayName("Логин через кнопку в форме регистрации")
    @Description("Проверяем, что попадаем на страницу логина через ссылку Войти на форме регистрации и можно" +
            "успешно залогиниться")
    @Test
    public void shouldLoginByButtonFromRegistrationForm() {
        RegisterPage registerPage = open(RegisterPage.URL, RegisterPage.class);
        LoginPage loginPage = registerPage.clickLinkButtonLogin();
        HomePage homePage = loginPage.makeUserLogin(user.getEmail(), user.getPassword());

        softAssertions.assertThat(homePage.isVisibleCreateOrderButton()).isTrue();
        softAssertions.assertAll();
    }

    @DisplayName("Логин через кнопку в форме восстановления пароля")
    @Description("Проверяем, что попадаем на страницу логина через ссылку Восстановить пароль на странице " +
            "восстановления пароля и можно успешно залогиниться")
    @Test
    public void shouldLoginByButtonFromForgotPasswordForm() {
        ForgotPassword forgotPassword = open(ForgotPassword.URL, ForgotPassword.class);
        LoginPage loginPage = forgotPassword.clickLinkButtonLogin();
        HomePage homePage = loginPage.makeUserLogin(user.getEmail(), user.getPassword());

        softAssertions.assertThat(homePage.isVisibleCreateOrderButton()).isTrue();
        softAssertions.assertAll();
    }
}

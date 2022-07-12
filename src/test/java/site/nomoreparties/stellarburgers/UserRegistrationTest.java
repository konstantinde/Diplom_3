package site.nomoreparties.stellarburgers;

import io.restassured.response.ValidatableResponse;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.apiclients.UserClient;
import site.nomoreparties.stellarburgers.helpers.UserGenerator;
import site.nomoreparties.stellarburgers.models.users.User;
import site.nomoreparties.stellarburgers.pageobject.LoginPage;
import site.nomoreparties.stellarburgers.pageobject.RegisterPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class UserRegistrationTest {

    SoftAssertions softAssertions = new SoftAssertions();
    User user;

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
    }

    @After
    public void tearDown() {
        closeWebDriver();
        UserClient userClient = new UserClient();
        ValidatableResponse userLogin = userClient.login(user);

        // Логинимся под пользователем, если успешно, то значит регистрация была, получаем токен и удаляем его
        if (userLogin.extract().body().path("success")) {
            userClient.delete(userLogin.extract().body().path("accessToken"));
        }
    }

    @Test
    public void shouldSuccessRegistration() {
        RegisterPage registerPage = open(RegisterPage.URL, RegisterPage.class);
        LoginPage loginPage = registerPage.makeUserRegistration(user.getName(), user.getEmail(), user.getPassword());

        softAssertions.assertThat(loginPage.isVisibleEnterText()).isTrue();
        softAssertions.assertThat(loginPage.isVisibleEnterButton()).isTrue();
        softAssertions.assertAll();
    }

    @Test
    public void shouldNotRegistrationWithWrongPassword() {
        RegisterPage registerPage = open(RegisterPage.URL, RegisterPage.class);
        registerPage.setName(user.getName());
        registerPage.setEmail(user.getEmail());
        // пароль обрежем до 5 символов
        registerPage.setPassword(user.getPassword().substring(0,5));
        registerPage.clickRegistrationButton();

        softAssertions.assertThat(registerPage.isVisibleTextWrongPassword()).isTrue();
    }

}

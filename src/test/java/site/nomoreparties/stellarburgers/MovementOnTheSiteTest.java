package site.nomoreparties.stellarburgers;

import io.restassured.response.ValidatableResponse;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.apiclients.UserClient;
import site.nomoreparties.stellarburgers.helpers.UserGenerator;
import site.nomoreparties.stellarburgers.models.users.User;
import site.nomoreparties.stellarburgers.pageobject.AccountProfilePage;
import site.nomoreparties.stellarburgers.pageobject.HomePage;
import site.nomoreparties.stellarburgers.pageobject.LoginPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class MovementOnTheSiteTest {

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

    @Test
    public void shouldOpenAccountProfileAfterClickPersonalAreaAfterLogin() {
        LoginPage loginPage = open(LoginPage.URL, LoginPage.class);
        HomePage homePage = loginPage.makeUserLogin(user.getEmail(), user.getPassword());
        AccountProfilePage accountProfilePage = homePage.clickPersonalAreaButtonAfterLogin();

        softAssertions.assertThat(accountProfilePage.isVisibleProfileLinkButton()).isTrue();
        softAssertions.assertThat(accountProfilePage.isVisibleOrderStoryLinkButton()).isTrue();
        softAssertions.assertThat(accountProfilePage.isVisibleExitLinkButton()).isTrue();
        softAssertions.assertThat(accountProfilePage.isVisibleAboutChapterText()).isTrue();
        softAssertions.assertAll();
    }

    @Test
    public void shouldOpenConstructorFromPersonalArea() {
        LoginPage loginPage = open(LoginPage.URL, LoginPage.class);
        HomePage homePage = loginPage.makeUserLogin(user.getEmail(), user.getPassword());
        AccountProfilePage accountProfilePage = homePage.clickPersonalAreaButtonAfterLogin();
        accountProfilePage.waitForAccountProfilePage();
        homePage.clickConstructorButton();
        homePage.waitForLoadHomePageAfterLogin();

        softAssertions.assertThat(homePage.isVisibleCreateOrderButton()).isTrue();
        softAssertions.assertThat(homePage.isVisibleCollectBurgerText()).isTrue();
        softAssertions.assertAll();
    }

    @Test
    public void shouldExitFromAccount() {
        LoginPage loginPage = open(LoginPage.URL, LoginPage.class);
        HomePage homePage = loginPage.makeUserLogin(user.getEmail(), user.getPassword());
        AccountProfilePage accountProfilePage = homePage.clickPersonalAreaButtonAfterLogin();
        accountProfilePage.clickExitLinkButton();
        loginPage.waitForLoadLoginPage();

        softAssertions.assertThat(loginPage.isVisibleEnterText()).isTrue();
        softAssertions.assertThat(loginPage.isVisibleEnterButton()).isTrue();
        softAssertions.assertAll();
    }
}

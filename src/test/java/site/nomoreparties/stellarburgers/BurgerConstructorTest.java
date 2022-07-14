package site.nomoreparties.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.pageobject.HomePage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class BurgerConstructorTest {

    SoftAssertions softAssertions = new SoftAssertions();

    @Before
    public void setUp() {
        // для запуска в Я.браузере
//        ChromeOptions options = new ChromeOptions();
//        System.setProperty("webdriver.chrome.driver", "C:\\chromeDriver\\chromedriver.exe");
//        options.setBinary("C:\\Users\\vorku\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
//        WebDriver driver = new ChromeDriver(options);
//        WebDriverRunner.setWebDriver(driver);

    }

    @After
    public void tearDown() {
        closeWebDriver();
    }

    @DisplayName("Переходы по разделам конструктора")
    @Description("Проверяем, что работают переходы к разделам Булки, Соусы, Начинки во фрейме конструктора, по " +
            "нажатии на соответствующие названия разделов фрейма")
    @Test
    public void shouldTransitionInChapterInConstructorFrame() {
        HomePage homePage = open(HomePage.URL, HomePage.class);
        homePage.waitForLoadHomePage();

        softAssertions.assertThat(homePage.isVisibleFrameTextBun()).isTrue();
        homePage.clickFrameButtonSauce();
        softAssertions.assertThat(homePage.isVisibleFrameTextSauce()).isTrue();
        homePage.clickFrameButtonFilling();
        softAssertions.assertThat(homePage.isVisibleFrameTextFilling()).isTrue();
        homePage.clickFrameButtonBun();
        softAssertions.assertThat(homePage.isVisibleFrameTextBun()).isTrue();
        softAssertions.assertAll();
    }
}

package site.nomoreparties.stellarburgers.pageobject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class AccountProfilePage extends BasePage{

    public static final String URL = "https://stellarburgers.nomoreparties.site/account/profile";

    // Пояснительный текст в профиле ЛК
    @FindBy(how = How.XPATH, using = "//p[text()='В этом разделе вы можете изменить свои персональные данные']")
    private SelenideElement textAboutChapter;

    // Кнопка Выход
    @FindBy(how = How.XPATH, using = "//button[text()='Выход']")
    private SelenideElement linkButtonExit;

    // Кнопка Профиль
    @FindBy(how = How.LINK_TEXT, using = "Профиль")
    private SelenideElement linkButtonProfile;

    // Кнопка История заказов
    @FindBy(how = How.LINK_TEXT, using = "История заказов")
    private SelenideElement linkButtonOrderStory;


    public void waitForAccountProfilePage() {
        textAboutChapter.shouldBe(visible);
    }

    public boolean isVisibleProfileLinkButton() {
        return linkButtonProfile.shouldBe(visible).isDisplayed();
    }

    public boolean isVisibleOrderStoryLinkButton() {
        return linkButtonOrderStory.shouldBe(visible).isDisplayed();
    }

    public boolean isVisibleExitLinkButton() {
        return linkButtonExit.shouldBe(visible).isDisplayed();
    }

    public boolean isVisibleAboutChapterText() {
        return textAboutChapter.shouldBe(visible).isDisplayed();
    }

    public HomePage clickConstructorButtonFromPersonalArea() {
        clickConstructorButton();
        HomePage homePage = page(HomePage.class);
        homePage.waitForLoadHomePageAfterLogin();
        return homePage;
    }

    public void clickExitLinkButton() {
        linkButtonExit.click();
    }

}

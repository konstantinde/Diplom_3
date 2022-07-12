package site.nomoreparties.stellarburgers.pageobject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;

public class FeedPage extends BasePage{

    public static final String URL = "https://stellarburgers.nomoreparties.site/feed";

    @FindBy(how = How.TAG_NAME, using = "h1")
    private SelenideElement textOrderFeed;

    @FindBy(how = How.XPATH, using = "//div/p[text()='Выполнено за сегодня']")
    private SelenideElement textCompletedToday;

    public void waitForFeedPage() {
        textCompletedToday.shouldBe(visible);
    }

}

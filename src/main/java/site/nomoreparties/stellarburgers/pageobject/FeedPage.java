package site.nomoreparties.stellarburgers.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;

public class FeedPage extends BasePage{

    public static final String URL = "https://stellarburgers.nomoreparties.site/feed";

    // Заголовое Лента заказов
    @FindBy(how = How.TAG_NAME, using = "h1")
    private SelenideElement textOrderFeed;

    // Текст Выполнено за сегодня
    @FindBy(how = How.XPATH, using = "//div/p[text()='Выполнено за сегодня']")
    private SelenideElement textCompletedToday;

    @Step("Загрузка страницы Лента заказов")
    public void waitForFeedPage() {
        textCompletedToday.shouldBe(visible);
    }

}

package site.nomoreparties.stellarburgers.pageobject;

import com.codeborne.selenide.WebDriverRunner;

public class BasePage {
    /**
     * Класс для для базовых элементов, которые присутствуют на всех страницах
     */

    public String getCurrentUrl(){
        // получаем текущий УРЛ, что в адресной строке
        return WebDriverRunner.getWebDriver().getCurrentUrl();
    }
}

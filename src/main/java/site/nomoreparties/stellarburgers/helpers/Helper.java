package site.nomoreparties.stellarburgers.helpers;

import java.util.List;
import java.util.Random;

public class Helper {
    /**
     * Вспомогательный класс для общих методов
     */

    public static String getRandomTestValue(List<String> valueList){
        // получаем случайное значение из списка тестовых данных
        Random random = new Random();
        return valueList.get(random.nextInt(valueList.size()));
    }

}


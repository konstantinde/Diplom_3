package site.nomoreparties.stellarburgers.apiclients;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import site.nomoreparties.stellarburgers.models.users.User;

import static io.restassured.RestAssured.given;

public class UserClient extends BaseRestClient {
    /**
     * Класс "клиент" пользователя. Содержит методы создания пользователя
     */

    private static final String USER_PATH = "/api/auth/";


    @Step("Авторизация пользователя в системе {user}")
    public ValidatableResponse login(User user) {
        return given()
                .spec(getBaseSpecRequest())
                .log().all()
                .body(user)
                .when()
                .post(USER_PATH + "login")
                .then()
                .log().all();
    }

    @Step("Регистрация пользователя с параметрами {user}")
    public ValidatableResponse register(User user) {
        return given()
                .spec(getBaseSpecRequest())
                .log().all()
                .body(user)
                .when()
                .post(USER_PATH + "register")
                .then()
                .log().all();
    }

    @Step("Удаление пользователя")
    public void delete(String bearerToken) {
        given()
                .spec(getDeleteUserRequest(bearerToken))
                .log().all()
                .when()
                .delete(USER_PATH + "user")
                .then()
                .log().all();
    }

}

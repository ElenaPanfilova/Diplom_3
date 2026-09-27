package stellarburgers.api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import stellarburgers.model.User;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserApi {

    private static final String BASE_URL = "https://qa-stellarburgers.education-services.ru";
    private static final String REGISTER_PATH = "/api/auth/register";
    private static final String LOGIN_PATH = "/api/auth/login";
    private static final String USER_PATH = "/api/auth/user";

    private RequestSpecification spec() {
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON);
    }

    @Step("Создать пользователя через API")
    public String create(User user) {
        return spec()
                .body(user)
                .when()
                .post(REGISTER_PATH)
                .then()
                .statusCode(200)
                .extract()
                .path("accessToken");
    }

    @Step("Получить токен пользователя через API")
    public String login(User user) {
        return spec()
                .body(Map.of("email", user.getEmail(), "password", user.getPassword()))
                .when()
                .post(LOGIN_PATH)
                .then()
                .extract()
                .path("accessToken");
    }

    @Step("Удалить пользователя через API")
    public void delete(String accessToken) {
        spec()
                .header("Authorization", accessToken)
                .when()
                .delete(USER_PATH);
    }
}

package user;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserRequest {


    private final String URL = "https://stellarburgers.nomoreparties.site/";

    private final String CREATE_API_ENDPOINT = "api/auth/register";

    private final String AUTHORIZATION_API_ENDPOINT = "api/auth/login";

    private final String DELETE_API_ENDPOINT = "api/auth/user";

    private final String UPDATE_API_ENDPOINT = "api/auth/user";

    // Запрос на создание нового пользвотеля
    public  ValidatableResponse successfulCreate(User user){
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(URL)
                .body(user)
                .when()
                .post(CREATE_API_ENDPOINT)
                .then().log().all();
    }


    // Запрос на создание нового пользвотеля без поля Name
    public ValidatableResponse errorWhenCreating(IncompleteUser incompleteUser){
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(URL)
                .body(incompleteUser)
                .when()
                .post(CREATE_API_ENDPOINT)
                .then().log().all();
    }

    // Запрос на авторизацию пользователя
    public ValidatableResponse successfulAuthorization(IncompleteUser incompleteUser) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(URL)
                .body(incompleteUser)
                .when()
                .post(AUTHORIZATION_API_ENDPOINT)
                .then().log().all();
    }

    // Запрос на удаление пользователя
    public ValidatableResponse deleteUser(String accessToken){
        return given().log().all()
                .contentType(ContentType.JSON)
                .headers("Authorization", accessToken)
                .baseUri(URL)
                .when()
                .delete(DELETE_API_ENDPOINT)
                .then().log().all();
    }


    // Запрос на успешное обноление данных пользователя
    public ValidatableResponse successUpdateDataUser(UpdateUser updateDataUser, String accessToken) {
       return given().log().all()
                .contentType(ContentType.JSON)
                .headers("Authorization", accessToken)
                .baseUri(URL)
                .when()
                .body(updateDataUser)
                .patch(UPDATE_API_ENDPOINT)
                .then().log().all();
    }

    // Запрос на обновление данных пользователя без токена
    public ValidatableResponse failedUpdateDataUser(UpdateUser updateDataUser) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(URL)
                .when()
                .body(updateDataUser)
                .patch(UPDATE_API_ENDPOINT)
                .then().log().all();
    }
}

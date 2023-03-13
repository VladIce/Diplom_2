package user;

import io.restassured.response.ValidatableResponse;
import static org.hamcrest.CoreMatchers.*;



public class UserResponse {
    public String successCreate(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("user.email", notNullValue())
                .body("user.name", notNullValue())
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue())
                .extract().path("accessToken");
    }

    public String errorWhenCreating(ValidatableResponse response) {
       return response.assertThat()
                .statusCode(403)
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"))
                .extract().path("accessToken");
    }

    public void errorWhenCreating2(ValidatableResponse response){
        response.assertThat()
                .statusCode(403)
                .body("success", equalTo(false))
                .body("message", equalTo("User already exists"));
    }


    public void successfulAuthorization(ValidatableResponse response){
        response.assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue())
                .body("user.email", notNullValue())
                .body("user.name", notNullValue());
    }

    public void authorizationWithInvalidEmailOrPassword(ValidatableResponse response){
        response.assertThat()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }

    // Успешное удаление пользователя 202 Accepted
    public void successDeleteUser(ValidatableResponse response) {
        response.assertThat()
                .statusCode(202)
                .body("success", equalTo(true))
                .body("message", equalTo("User successfully removed"));



    }

    // Успешное обновление пользователя 200 ОК
    public void successUpdateUser(ValidatableResponse response){
        response.assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("user.email", notNullValue())
                .body("user.name", notNullValue());
    }

    // Ошибка при обновлении пользователя 401 Unauthorized
    public void failedUpdateUser(ValidatableResponse response){
        response.assertThat()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", notNullValue());
    }





}





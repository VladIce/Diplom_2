package usertest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import user.*;


public class UserAuthorizationTest {




    CreateUserData createUser = new CreateUserData();
    User user = createUser.random();
    IncompleteUser incompleteUser = new IncompleteUser(user.getEmail(), user.getPassword());

    IncompleteUser incompleteUser1 = new IncompleteUser(incompleteUser.invalidLogin(), incompleteUser.getPassword());
    IncompleteUser incompleteUser2 = new IncompleteUser(incompleteUser.getEmail(), incompleteUser.invalidPass());

    UserRequest request = new UserRequest();
    UserResponse response = new UserResponse();
    private String accessToken;


    // Создаем нового пользовтеля
    @Before
    public void successfulCreationOfNewUser() {
        ValidatableResponse createUser = request.successfulCreate(user);
        accessToken = response.successCreate(createUser);
    }

    @DisplayName("Проверить успешную авторизацию пользователя")
    @Description("Авторизация под существующим пользователем")// Успешная авторизация пользовтеля
    @Test
    public void successfulAuthorizationUser(){
    ValidatableResponse authorization = request.successfulAuthorization(incompleteUser);
    response.successfulAuthorization(authorization);
    }

    @DisplayName("Проверить авторизация пользователя с невалидным Email")
    @Description("Авторизация с неверным логином")
    @Test
    public void authorizationWithInvalidEmail(){
        ValidatableResponse failedAuthorization = request.successfulAuthorization(incompleteUser1);
        response.authorizationWithInvalidEmailOrPassword(failedAuthorization);
    }

    @DisplayName("Проверить авторизация пользователя с невалидным Password")
    @Description("Авторизация с неверным паролем")
    @Test
    public void authorizationWithInvalidPassword(){
    ValidatableResponse failAuthorization = request.successfulAuthorization(incompleteUser2);
    response.authorizationWithInvalidEmailOrPassword(failAuthorization);
    }

    // Удаление пользователя
    @After
    public void deleteUser() {
        if (accessToken != null) {
            request.deleteUser(accessToken);
        }
    }
}

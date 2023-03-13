package usertest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import user.*;

public class CreateUserTest {

    CreateUserData createUser = new CreateUserData();
    User user = createUser.random();

    User existingUser = createUser.existingUser();

    UserRequest request = new UserRequest();
    UserResponse response = new UserResponse();
    private String accessToken;

    @DisplayName("Проверить создание нового пользователя")
    @Test
    public void successfulCreateOfNewUser() {
        ValidatableResponse createUser = request.successfulCreate(user);
        accessToken = response.successCreate(createUser);
    }

    @DisplayName("Проверить создание пользователя, который уже зарегистрирован")
    @Test
    public void createUserWithExistingData() {
        ValidatableResponse createUser = request.successfulCreate(existingUser);
        response.errorWhenCreating2(createUser);
    }

    @DisplayName("Проверить создание пользователя и не заполнить одно из обязательных полей")
    @Description("Проверяем запрос без обязательного поля Name")
    @Test
    public void createNewUserWithoutNecessarilyField(){
        IncompleteUser incompleteUser = new IncompleteUser(user.getEmail(), user.getPassword());
        ValidatableResponse failCreateUser = request.errorWhenCreating(incompleteUser);
        accessToken = response.errorWhenCreating(failCreateUser);
    }

    // Удаление созданого пользователя
    @After
    public void deleteUser(){
        if(accessToken != null) {
            request.deleteUser(accessToken);
        }
    }
}
















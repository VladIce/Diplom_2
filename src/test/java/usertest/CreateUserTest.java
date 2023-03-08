package usertest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import user.*;

import static io.restassured.RestAssured.given;

public class CreateUserTest {

    CreateUserData createUser = new CreateUserData();
    User user = createUser.random();

    UserRequest request = new UserRequest();
    UserResponse response = new UserResponse();

    public String accessToken;

    // Создание нового пользователя
    @Before
    public void successfulCreationOfNewUser() {
        ValidatableResponse createUser = request.successfulСreation(user);
        accessToken = response.successCreate(createUser);
    }

    @DisplayName("Проверить создание пользователя, который уже зарегистрирован")
    @Test
    public void creatingUserWithExistingData() {
        ValidatableResponse createUser = request.successfulСreation(user);
        response.errorWhenCreating2(createUser);
    }

    @DisplayName("Проверить создание пользователя и не заполнить одно из обязательных полей")
    @Description("Проверяем запрос без обязательного поля Name")
    @Test
    public void creatingNewUserWithoutNecessarilyField(){
        IncompleteUser incompleteUser = new IncompleteUser(user.getEmail(), user.getPassword());
        ValidatableResponse failCreateUser = request.errorWhenCreating(incompleteUser);
        response.errorWhenCreating(failCreateUser);
    }

    // Удаление созданого пользователя
    @After
    public void deleteUser(){
        ValidatableResponse deleteUser = request.deleteUser(accessToken);
        response.successDeleteUser(deleteUser);
    }
}
















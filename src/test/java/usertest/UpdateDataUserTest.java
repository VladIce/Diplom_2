package usertest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import user.*;

import static io.restassured.RestAssured.given;

public class UpdateDataUserTest {


    CreateUserData createUser = new CreateUserData();
    User user = createUser.random();

    UpdateUser updateDataUser = createUser.randomUpdateData();

    UserRequest request = new UserRequest();
    UserResponse response = new UserResponse();

    private String accessToken;

    // Создание нового пользователя
    @Before
    public void successfulCreationOfNewUser() {
        ValidatableResponse createUser = request.successfulCreate(user);
        accessToken = response.successCreate(createUser);
    }

    @DisplayName("Проверить обновление данных пользователя")
    @Description("Изменение данных пользователя с авторизацией")
    @Test
    public void successUpdateDataUser() {
        ValidatableResponse fullUpdateDataUser = request.successUpdateDataUser(updateDataUser, accessToken);
        response.successUpdateUser(fullUpdateDataUser);
    }

    @DisplayName("Проверить обновление email пользователя")
    @Description("Изменение email пользователя с авторизацией")
    @Test
    public void successUpdateDataUserEmail() {
        UpdateUser updateDataUserEmail = new UpdateUser(updateDataUser.getEmail(), user.getName());
        ValidatableResponse updateEmailUser = request.successUpdateDataUser(updateDataUserEmail, accessToken);
        response.successUpdateUser(updateEmailUser);
    }

    // Обновляем поле Name у пользователя
    @DisplayName("Проверить обновление имя пользователя")
    @Description("Изменение имя пользователя с авторизацией")
    @Test
    public void successUpdateDataUserName() {
        UpdateUser updateDataUserName = new UpdateUser(user.getEmail(), updateDataUser.getName());
        ValidatableResponse updateNameUser = request.successUpdateDataUser(updateDataUserName, accessToken);
        response.successUpdateUser(updateNameUser);
    }

    @DisplayName("Проверить обновление данных пользователя")
    @Description("Изменение данных пользователя без авторизации")
    @Test
    public void failedUpdateDataUser() {
        ValidatableResponse failedFullUpdateUser = request.failedUpdateDataUser(updateDataUser);
        response.failedUpdateUser(failedFullUpdateUser);
    }

    @DisplayName("Проверить обновление email пользователя")
    @Description("Изменение email пользователя без авторизации")
    @Test
    public void failedUpdateDataUserEmail() {
        UpdateUser updateDataUserEmail = new UpdateUser(updateDataUser.getEmail(), user.getName());
        ValidatableResponse failedUpdateDataEmailUser = request.failedUpdateDataUser(updateDataUserEmail);
        response.failedUpdateUser(failedUpdateDataEmailUser);
    }

    @DisplayName("Проверить обновление имя пользователя")
    @Description("Изменение имя пользователя без авторизации")
    @Test
    public void failedUpdateDataUserName(){
        UpdateUser updateDataUserName = new UpdateUser(user.getEmail(), updateDataUser.getName());
        ValidatableResponse failedUpdateDataNameUser = request.failedUpdateDataUser(updateDataUserName);
        response.failedUpdateUser(failedUpdateDataNameUser);
    }

    // Удаление пользователя
    @After
    public void deleteUser() {
        if (accessToken != null) {
            request.deleteUser(accessToken);
        }
    }
}

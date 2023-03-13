package ordertest;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import order.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import user.CreateUserData;
import user.User;
import user.UserRequest;
import user.UserResponse;
import java.util.ArrayList;
import java.util.List;

public class CreateOrderTest {

    CreateOrderBody createOrderBody = new CreateOrderBody(new ArrayList<>(List.of("61c0c5a71d1f82001bdaaa6d")));
    CreateOrderBody createOrderBody1 = new CreateOrderBody();
    CreateOrderBody createOrderBody2 = new CreateOrderBody(new ArrayList<>(List.of("61c0c5a71d1f82001bdaaa6@@@@@@d")));
    CreateUserData createUser = new CreateUserData();
    User user = createUser.random();
    OrderRequest orderRequest = new OrderRequest();
    OrderResponse orderResponse = new OrderResponse();
    UserRequest request = new UserRequest();
    UserResponse response = new UserResponse();


    private String accessToken;

    // Создание нового пользователя
    @Before
    public void successfulCreationOfNewUser() {
        ValidatableResponse createUser = request.successfulCreate(user);
        accessToken = response.successCreate(createUser);
    }


    @DisplayName("Проверить создание заказа без авторизации")
    @Test
    public void createOrdersNotAuthorizationUser() {
        ValidatableResponse successCreateOrder = orderRequest.createOrderWithoutUserAuthorization(createOrderBody);
        orderResponse.successfulCreateOrders(successCreateOrder);
    }

    @DisplayName("Проверить создание заказа с авторизацией")
    @Test
    public void createOrdersTheAuthorizationUser() {
        ValidatableResponse createOrder = orderRequest.createOrdersTheAuthorization(createOrderBody, accessToken);
        orderResponse.successfulCreateOrders(createOrder);
    }

    @DisplayName("Проверить создание заказа без ингредиентов")
    @Test
    public void createOrderWithoutIngredients() {
        ValidatableResponse createOrder = orderRequest.createOrderWithoutUserAuthorization(createOrderBody1);
        orderResponse.failedCreateOrders(createOrder);
    }

    @DisplayName("Проверить создание заказа с неверным хешем ингредиентов")
    @Test
    public void createOrderWithIncorrectHashIngredients() {
        ValidatableResponse createOrder = orderRequest.createOrderWithoutUserAuthorization(createOrderBody2);
        orderResponse.createOrderInvalidHash(createOrder);
    }

    // Удаление созданого пользовтеля
    @After
    public void deleteUser() {
        if (accessToken != null) {
            request.deleteUser(accessToken);
        }
    }
}

package ordertest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import order.OrderRequest;
import order.OrderResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import user.CreateUserData;
import user.User;
import user.UserRequest;
import user.UserResponse;

public class GetOrderTest {

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

    @DisplayName("Проверить получение заказов конкретного пользователя")
    @Description("Проверяем заказы авторизованного пользователя")
    @Test
    public void getOrderAuthorizedUser(){
        ValidatableResponse userOrder = orderRequest.getOrderAuthorizationUser(accessToken);
        orderResponse.getOrderAuthorizationUser(userOrder);
    }

    @DisplayName("Проверить получение заказов конкретного пользователя")
    @Description("Проверяем заказы неавторизованного пользователя")
    @Test
    public void getOrderUnauthorizedUser(){
        ValidatableResponse userOrder = orderRequest.getOrderNotAuthorizationUser();
        orderResponse.getOrderNotAuthorizationUser(userOrder);
    }

    // Удаление созданого пользовтеля
    @After
    public void deleteUser() {
        if (accessToken != null) {
            request.deleteUser(accessToken);
        }
    }
}

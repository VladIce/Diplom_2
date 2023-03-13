package order;

import io.restassured.response.ValidatableResponse;
import org.junit.Assert;

import static org.hamcrest.CoreMatchers.*;

public class OrderResponse {


    // Успешное создание заказа 200 ОК
    public void successfulCreateOrders(ValidatableResponse response){
            ResponseSuccessfulOrderCreation orderStatusCode = response
                .statusCode(200)
                .extract()
                .as(ResponseSuccessfulOrderCreation.class);
        Assert.assertTrue("success", true);
        Assert.assertNotNull(orderStatusCode.getName());
        Assert.assertNotNull(orderStatusCode.getOrder());
        System.out.println(orderStatusCode.isSuccess());
        System.out.println(orderStatusCode.getName());
        System.out.println(orderStatusCode.getOrder());
    }

    // Создание заказа без ингридиентов 400 Bad Request
    public void failedCreateOrders(ValidatableResponse response){

        ResponseCreatingOrderWithoutIngredients responseCreatingOrderWithoutIngredients = response
                .statusCode(400)
                .extract()
                .as(ResponseCreatingOrderWithoutIngredients.class);
        Assert.assertFalse("succes", false);
        Assert.assertEquals("Ingredient ids must be provided", responseCreatingOrderWithoutIngredients.getMessage());
    }

    // Созание заказа с неверным хешем ингридиента
    public void createOrderInvalidHash(ValidatableResponse response){
        response.assertThat().statusCode(500);
    }


    // Получение списка заказа конкретного пользователя с авторизацей
    public void getOrderAuthorizationUser(ValidatableResponse response){
        response.assertThat().statusCode(200)
                .body("success", equalTo(true))
                .body("total", notNullValue())
                .body("totalToday", notNullValue());
    }




    // Получение списка заказа конкретного пользователя без авторизации
    public void getOrderNotAuthorizationUser(ValidatableResponse response){
        response.assertThat().statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));
    }
}

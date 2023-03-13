package order;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderRequest {


    private final String URL = "https://stellarburgers.nomoreparties.site/";
    private final String ORDERS_API_ENDPOINT = "api/orders";



    // Создание заказа без авторизации пользователя
    public ValidatableResponse createOrderWithoutUserAuthorization(CreateOrderBody createOrderBody){
        return  given().log().all()
                .contentType(ContentType.JSON)
                .body(createOrderBody)
                .baseUri(URL)
                .when()
                .post(ORDERS_API_ENDPOINT)
                .then().log().all();


    }

    // Создание заказа с авторизацей пользователя
    public ValidatableResponse createOrdersTheAuthorization(CreateOrderBody order, String accessToken) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .headers("Authorization", accessToken)
                .baseUri(URL)
                .body(order)
                .when()
                .post(ORDERS_API_ENDPOINT)
                .then().log().all();
    }

    // Получение заказа конкретного пользователя без авторизации
    public ValidatableResponse getOrderNotAuthorizationUser(){
        return  given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(URL)
                .when()
                .get(ORDERS_API_ENDPOINT)
                .then().log().all();


    }

    // Получение заказа конкретного пользователя с авторизацей
    public ValidatableResponse getOrderAuthorizationUser( String accessToken) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .headers("Authorization", accessToken)
                .baseUri(URL)
                .when()
                .get(ORDERS_API_ENDPOINT)
                .then().log().all();
    }
}

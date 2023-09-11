package com.ArtCake.restAssuredTests;

import io.restassured.http.Cookie;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RAFinishedOrderTests extends TestBase {

    @Test
    public void MovingOrderToFinishedSuccessfulTest() {
        Cookie sessionCookie = loginAsConditioner();
        int orderId = 2;

        given()
                .cookie(sessionCookie)
                .queryParam("orderId", orderId)
                .when()
                .put("/api/orders/" + orderId + "/done?" + orderId)
                .then()
                .assertThat().statusCode(200);

    }

    @Test
    public void MovingOrderToFinishedWithUserData() {
        Cookie sessionCookie = loginAsClient();
        int orderId = 2;

        given()
                .cookie(sessionCookie)
                .queryParam("orderId", orderId)
                .when()
                .put("/api/orders/" + orderId + "/done?" + orderId)
                .then()
                .assertThat().statusCode(403);
    }

    @Test
    public void MovingOrderToFinishedWithUnexcitingOrderIdTest() {
        Cookie sessionCookie = loginAsConditioner();
        int orderId = 777;

        given()
                .cookie(sessionCookie)
                .queryParam("orderId", orderId)
                .when()
                .put("/api/orders/" + orderId + "/done?" + orderId)
                .then()
                .assertThat().statusCode(404);
    }
}

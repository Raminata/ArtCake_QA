package com.ArtCake.restAssuredTests;
import com.ArtCake.dto.OrderRequestDto;

import io.restassured.http.Cookie;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RAGetClientOrdersTests extends TestBase {

    @Test
    public void getAllOrdersForClientAsClient200() {
        int page = 0;

        Cookie sessionCookie = loginAsClient();

        given()
                .cookie(sessionCookie)
                .queryParam("page", page)
                .when()
                .get("/api/users/client/orders")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(OrderRequestDto.class);

    }

    @Test
    public void getAllOrdersForClientAsManager200() {
        int page = 0;

        Cookie sessionCookie = loginAsManager();

        given()
                .cookie(sessionCookie)
                .queryParam("page", page)
                .when()
                .get("/api/users/client/orders")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(OrderRequestDto.class);

    }

    @Test
    public void getAllOrdersForClientAsConfectioner200() {
        int page = 0;

        Cookie sessionCookie = loginAsConditioner();

        given()
                .cookie(sessionCookie)
                .queryParam("page", page)
                .when()
                .get("/api/users/client/orders")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(OrderRequestDto.class);
    }

}

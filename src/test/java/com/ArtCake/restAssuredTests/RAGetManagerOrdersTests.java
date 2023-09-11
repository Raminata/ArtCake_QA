package com.ArtCake.restAssuredTests;
import com.ArtCake.dto.OrderRequestDto;

import io.restassured.http.Cookie;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RAGetManagerOrdersTests extends TestBase {

    @Test
    public void getAllOrdersAsManager200() {
        int page = 2;

        Cookie sessionCookie = loginAsManager();

        given()
                .cookie(sessionCookie)
                .queryParam("page", page)
                .when()
                .get("/api/users/manager/orders")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(OrderRequestDto.class);
    }

    @Test
    public void getAllOrdersAsManager401() {
        int page = 0;

        given()
                .queryParam("page", page)
                .when()
                .get("/api/users/manager/orders")
                .then()
                .assertThat().statusCode(401)
                .extract().response().as(OrderRequestDto.class);
    }
}

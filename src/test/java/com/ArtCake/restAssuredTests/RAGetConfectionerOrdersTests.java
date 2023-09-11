package com.ArtCake.restAssuredTests;
import com.ArtCake.dto.OrderRequestDto;
import io.restassured.http.Cookie;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RAGetConfectionerOrdersTests extends TestBase {

    @Test
    public void getAllOrdersAsConfectioner200() {
        int page = 0;

        Cookie sessionCookie = loginAsConditioner();

        given()
                .cookie(sessionCookie)
                .queryParam("page", page)
                .when()
                .get("/api/users/confectioner/orders")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(OrderRequestDto.class);
    }

    @Test
    public void getAllOrdersAsConfectioner401() {
        int page = 0;

        given()
                .queryParam("page", page)
                .when()
                .get("/api/users/confectioner/orders")
                .then()
                .assertThat().statusCode(401)
                .extract().response().as(OrderRequestDto.class);
    }
}

package com.ArtCake.restAssuredTests;

import com.ArtCake.dto.OrderRequestDto;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RAGettingProfileTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        Cookie sessionCookie = loginAsClient();

        int cakeID = 1;

        OrderRequestDto orderRequestDto = OrderRequestDto.builder()
                .count(3)
                .deadline("2023-10-10")
                .clientWishes("Make in blue and white colours")
                .build();

        given()
                .contentType(ContentType.JSON)
                .cookie(sessionCookie)
                .body(orderRequestDto)
                .when()
                .post("/api/orders/cakes/" + cakeID + "?cakeId=" + cakeID)
                .then()
                .assertThat().statusCode(201);
    }


    @Test
    public void gettingProfileFail404Test() {
        Cookie sessionCookie = loginAsClient();

        given()
                .contentType(ContentType.JSON)
                .cookie(sessionCookie)
                .when()
                .get("/api/users/me" + -100 + "/decline?orderId=" + -100)
                .then()
                .assertThat().statusCode(404);
    }

    @Test
    public void gettingProfileSuccessTest() {
        Cookie sessionCookie = loginAsClient();

        given()
                .contentType(ContentType.JSON)
                .cookie(sessionCookie)
                .when()
                .get("/api/users/me")
                .then()
                .assertThat().statusCode(200);
    }
}
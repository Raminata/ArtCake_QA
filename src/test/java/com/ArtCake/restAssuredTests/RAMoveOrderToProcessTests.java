package com.ArtCake.restAssuredTests;

import com.ArtCake.dto.MoveOrderToProcessDto;
import com.ArtCake.dto.OrderRequestDto;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RAMoveOrderToProcessTests extends TestBase {

    private static String orderId = "";

    @BeforeMethod
    public void precondition() {
        Cookie sessionCookie = loginAsClient();

        int cakeID = 1;

        OrderRequestDto orderRequestDto = OrderRequestDto.builder()
                .count(3)
                .deadline("2023-10-10")
                .clientWishes("Make in blue and white colours")
                .build();

        orderId = given()
                .contentType(ContentType.JSON)
                .cookie(sessionCookie)
                .body(orderRequestDto)
                .when()
                .post("/api/orders/cakes/" + cakeID + "?cakeId=" + cakeID)
                .then()
                .assertThat().statusCode(201)
                .extract().body().jsonPath().getString("id");
    }

    @Test
    public void moveOrderToProcessFailTest() {
        Cookie sessionCookie = loginAsClient();

        MoveOrderToProcessDto dto = MoveOrderToProcessDto.builder()
                .confectionerId(2)
                .build();

        given()
                .contentType(ContentType.JSON)
                .cookie(sessionCookie)
                .body(dto)
                .when()
                .put("/api/orders/" + orderId + "?orderId=" + orderId)
                .then()
                .assertThat().statusCode(403);
    }

    @Test
    public void moveOrderToProcessSuccessTest() {
        Cookie sessionCookie = loginAsManager();

        MoveOrderToProcessDto dto = MoveOrderToProcessDto.builder()
                .confectionerId(2)
                .build();

        given()
                .contentType(ContentType.JSON)
                .cookie(sessionCookie)
                .body(dto)
                .when()
                .put("/api/orders/" + orderId + "?orderId=" + orderId)
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void moveOrderToProcessFail404Test() {
        Cookie sessionCookie = loginAsManager();

        MoveOrderToProcessDto dto = MoveOrderToProcessDto.builder()
                .confectionerId(2)
                .build();

        given()
                .contentType(ContentType.JSON)
                .cookie(sessionCookie)
                .body(dto)
                .when()
                .put("/api/orders/" + -100 + "?orderId=" + -100)
                .then()
                .assertThat().statusCode(404);
    }
}
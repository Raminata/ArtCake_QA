package com.ArtCake.restAssuredTests;

import com.ArtCake.dto.CakeResponseDto;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RAGetCakeTests extends TestBase {
    @Test
    public void getCakeByIDSuccessfulTest() {
        int cakeID = 2;

        CakeResponseDto cakeResponseDto = given()
                .body(cakeID)
                .when()
                .get("/api/cakes/" + cakeID)
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(CakeResponseDto.class);

        System.out.println(cakeResponseDto.getName());
    }

    @Test
    public void getCakeByIDTestError404() {
        int cakeID = 322;

        given()
                .body(cakeID)
                .when()
                .get("/api/cakes/" + cakeID)
                .then()
                .assertThat().statusCode(404);

    }
}

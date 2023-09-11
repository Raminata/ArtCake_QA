package com.ArtCake.restAssuredTests;

import com.ArtCake.dto.CakeResponseDto;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class RACakesGetByCategoryTests {
    @Test
    public void getCakeByCategoryTest200() {
        String category = "MOUSSE";
        given()
                .queryParam("category", category)
                .when()
                .get("/api/cakes/category/"+category)
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(CakeResponseDto.class);
    }

    @Test
    public void getCakeByCategory404() {
        String category = "MOUS";
        given()
                .queryParam("category", category)
                .when()
                .get("/api/cakes/category/"+category)
                .then()
                .assertThat().statusCode(404)
                .extract().response().as(CakeResponseDto.class);
    }
}

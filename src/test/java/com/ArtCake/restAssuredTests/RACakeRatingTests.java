package com.ArtCake.restAssuredTests;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RACakeRatingTests extends TestBase {

    @Test
    public void getCakeSalesRating200() {

        int cakeId = 1;

        given()
                .contentType(ContentType.JSON)
                .body(cakeId)
                .when()
                .get("/api/cakes/sales")
                .then()
                .assertThat().statusCode(200);

    }



    @Test
    public void getCakeSalesRating404() {
        int cakeId = 999; // Assume this is an ID that doesn't exist

        given()
                .contentType(ContentType.JSON)
                .pathParam("cakeId", cakeId)
                .when()
                .get("/api/cakes/sales/{cakeId}")
                .then()
                .assertThat()
                .statusCode(404);
    }
}


package com.ArtCake.restAssuredTests;

import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RAGetAllCakesTests extends TestBase {
    @Test
    public void getAllCakesSuccess() {
        Cookie sessionCookie = loginAsConditioner();

        given()
                .contentType(ContentType.JSON)
                .cookie(sessionCookie)
                .when()
                .get("/api/cakes?page=1")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void getAllCakesFail400() {
        Cookie sessionCookie = loginAsConditioner();

        given()
                .contentType(ContentType.JSON)
                .cookie(sessionCookie)
                .when()
                .get("/api/cakes")
                .then()
                .assertThat().statusCode(400);
    }
}

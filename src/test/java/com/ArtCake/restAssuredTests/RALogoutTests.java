package com.ArtCake.restAssuredTests;


import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class RALogoutTests extends TestBase {

    @Test
    public void logoutSuccessAsClientTest() {
        Cookie sessionCookie = loginAsClient();
        given()
                .contentType(ContentType.JSON)
                .cookie(sessionCookie)
                .when()
                .post("/api/logout")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void logoutSuccessAsConditionerTest() {
        Cookie sessionCookie = loginAsConditioner();
        given()
                .contentType(ContentType.JSON)
                .cookie(sessionCookie)
                .when()
                .post("/api/logout")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void logoutSuccessAsManagerTest() {
        Cookie sessionCookie = loginAsManager();
        given()
                .contentType(ContentType.JSON)
                .cookie(sessionCookie)
                .when()
                .post("/api/logout")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void logoutFailTest() {
        given()
                .contentType(ContentType.JSON)
                .cookie("IUJHEKJQH$EBJSANDKJ")
                .when()
                .post("/api/logout")
                .then()
                .assertThat().statusCode(200); // TODO ASK Backend
    }
}

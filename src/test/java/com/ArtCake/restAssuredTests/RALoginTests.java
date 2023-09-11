package com.ArtCake.restAssuredTests;


import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertNotNull;


public class RALoginTests extends TestBase {

    @Test
    public void loginSuccessAsClientTest() {
        Cookie sessionCookie = loginAsClient();
        assertNotNull(sessionCookie);
    }

    @Test
    public void loginSuccessAsConditionerTest() {
        Cookie sessionCookie = loginAsConditioner();
        assertNotNull(sessionCookie);
    }

    @Test
    public void loginSuccessAsManagerTest() {
        Cookie sessionCookie = loginAsManager();
        assertNotNull(sessionCookie);
    }

    @Test
    public void loginFailTest() {
        given()
                .contentType(ContentType.URLENC)
                .formParam("username", "wrong.email@gmail.com")
                .formParam("password", "213")
                .when()
                .post("/api/login")
                .then()
                .assertThat().statusCode(401);
    }
}

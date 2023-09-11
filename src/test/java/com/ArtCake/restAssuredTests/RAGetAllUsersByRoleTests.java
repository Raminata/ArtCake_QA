package com.ArtCake.restAssuredTests;

import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RAGetAllUsersByRoleTests extends TestBase {

    @Test
    public void testGetAllUsersByRole200() {
        String role = "CONFECTIONER";
        Cookie sessionCookie = loginAsManager();

        given()
                .cookie(sessionCookie)
                .queryParam("role", role)
                .when()
                .get("/api/users/role/" + role)
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void testGetAllUsersByRole401() {
        String role = "CONFECTIONER";

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/users/role/" + role)
                .then()
                .assertThat().statusCode(401);
    }

    @Test
    public void testGetAllUsersByRole404() {
        String nonExistentRole = "NON_EXISTENT_ROLE";
        Cookie sessionCookie = loginAsManager();

        given()
                .cookie(sessionCookie)
                .queryParam("role", nonExistentRole)
                .when()
                .get("/api/users/role/" + nonExistentRole)
                .then()
                .assertThat().statusCode(404);
    }

}

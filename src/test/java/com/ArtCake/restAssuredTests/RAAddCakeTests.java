package com.ArtCake.restAssuredTests;

import com.ArtCake.dto.CakeCreateRequestDto;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class RAAddCakeTests extends TestBase {


    @Test
    public void createCakeSuccessTest() {
        given()

                .when()
                .post("/api/logout")
                .then();

        Cookie sessionCookie = loginAsManager();

        CakeCreateRequestDto dto = CakeCreateRequestDto.builder()
                .name("blueberry-cupcake-" + UUID.randomUUID())
                .ingredients("milk,egg,salt...")
                .price(70.5)
                .category("CUPCAKES")
                .state("CREATED")
                .build();

        given()
                .contentType(ContentType.JSON)
                .cookie(sessionCookie)
                .body(dto)
                .when()
                .post("/api/cakes")
                .then()
                .assertThat().statusCode(201);
    }

    @Test
    public void createCakeFailed401Test() {
        Cookie sessionCookie = loginAsClient();

        CakeCreateRequestDto dto = CakeCreateRequestDto.builder()
                .name("blueberry-cupcake")
                .ingredients("milk,egg,salt...")
                .category("CUPCAKE")
                .price(70.5)
                .state("CREATED")
                .build();

        given()
                .contentType(ContentType.JSON)
                .cookie(sessionCookie)
                .body(dto)
                .when()
                .post("/api/cakes")
                .then()
                .assertThat().statusCode(403);
    }
}


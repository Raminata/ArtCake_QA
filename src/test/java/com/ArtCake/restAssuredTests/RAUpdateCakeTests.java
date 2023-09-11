package com.ArtCake.restAssuredTests;

import com.ArtCake.dto.CakeUpdateRequestDto;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RAUpdateCakeTests extends TestBase {

    @Test
    public void updateCakeByIdSuccessfulTest() {
        int cakeId = 1;
        Cookie sessionCookie = loginAsManager();

        CakeUpdateRequestDto cakeUpdateRequestDto = CakeUpdateRequestDto.builder()
                .name("vanilla-cupcake")
                .ingredients("sugar, butter, eggs")
                .price(32.87)
                .state("CREATED")
                .build();

        given()
                .cookie(sessionCookie)
                .queryParam("cake-id", cakeId)
                .contentType(ContentType.JSON)
                .body(cakeUpdateRequestDto)
                .when()
                .put("api/cakes/" + cakeId)
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void updateCakeWithUserAccessError403Test() {
        Cookie sessionCookie = loginAsClient();
        int cakeId = 1;

        CakeUpdateRequestDto cakeUpdateRequestDto = CakeUpdateRequestDto.builder()
                .name("vanilla-cupcake")
                .ingredients("sugar, butter, eggs")
                .price(32.87)
                .state("CREATED")
                .build();

        given()
                .cookie(sessionCookie)
                .queryParam("cake-id", cakeId)
                .contentType(ContentType.JSON)
                .body(cakeUpdateRequestDto)
                .when()
                .put("api/cakes/" + cakeId)
                .then()
                .assertThat().statusCode(403);
    }
}
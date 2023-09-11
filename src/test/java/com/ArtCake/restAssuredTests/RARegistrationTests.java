package com.ArtCake.restAssuredTests;


import com.ArtCake.dto.RegistrationRequestDto;
import com.ArtCake.dto.RegistrationResponseDto;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;


public class RARegistrationTests {

    @Test
    public void registrationSuccessTest() {
        RegistrationRequestDto login = RegistrationRequestDto.builder()
                .firstName("Lor")
                .lastName("Jackson")
                .email(UUID.randomUUID() + "@mail.com")
                .password("Qwerty123!")
                .town("Berlin")
                .zipCode("22331")
                .street("Sonnenallee")
                .houseNumber(17)
                .phoneNumber("+4917612930456")
                .build();

        RegistrationResponseDto responseDto = given()
                .contentType(ContentType.JSON)
                .body(login)
                .when()
                .post("/api/registration")
                .then()
                .assertThat().statusCode(201)
                .extract().response().as(RegistrationResponseDto.class);

        System.out.println(responseDto.getRole());
    }

    @Test
    public void registrationWithWrongFormatEmailTest() {
        RegistrationRequestDto auth = RegistrationRequestDto.builder()
                .firstName("Lor")
                .lastName("Jackson")
                .email("arkemail.com")
                .password("Qwerty123!")
                .town(" Berlin")
                .street("Sonnenallee")
                .houseNumber(17)
                .phoneNumber("+4917612930456")
                .build();

        given()
                .contentType(ContentType.JSON)
                .body(auth)
                .when()
                .post("/api/registration")
                .then()
                .assertThat().statusCode(400)
                .extract().response().prettyPrint();
    }
}

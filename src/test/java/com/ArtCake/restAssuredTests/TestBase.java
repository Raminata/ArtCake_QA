package com.ArtCake.restAssuredTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class TestBase {
    final static Logger logger = LoggerFactory.getLogger(TestBase.class);

    public static Cookie loginAsClient() {
        return loginWithUser("client@gmail.com", "Client007!");
    }

    public static Cookie loginAsManager() {
        return loginWithUser("manager@mail.com", "Manager007!");
    }

    public static Cookie loginAsConditioner() {
        return loginWithUser("konditerow@gmail.com", "Qwerty123!");
    }

    public static Cookie loginWithUser(String email, String password) {
        Response loginResponse = given()
                .contentType(ContentType.URLENC)
                .formParam("username", email)
                .formParam("password", password)
                .when()
                .post("/api/login");

        return loginResponse.getDetailedCookie("JSESSIONID");
    }

    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI = "http://localhost:8080";
    }
    @BeforeMethod
    public void setUp(Method method, Object[] parameters){
        logger.info("Start test "+method.getName()+" with parameters:"+ Arrays.asList(parameters));

    }
    @AfterMethod
    public void quit(){
        logger.info("Stop test");
    }
}

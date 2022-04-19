package io.vteial.workbench.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class DefaultControllerTest {

    @Test
    public void ping() {
        given()
                .when()
                .get("/ping")
                .then()
                .statusCode(200)
                .body(is("Ping Pong!!!"));
    }

    @Test
    public void index() {
        given()
                .when()
                .get("/index")
                .then()
                .statusCode(200)
                .body(containsString("SalesTap"));
    }
}

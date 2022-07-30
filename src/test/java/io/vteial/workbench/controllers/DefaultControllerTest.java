package io.vteial.workbench.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class DefaultControllerTest {

    @ConfigProperty(name = "app.name")
    String appName;

    @Test
    public void index() {
        given()
                .when()
                .get("/index")
                .then()
                .statusCode(200);
    }

    @Test
    public void ping() {
        given()
                .when()
                .get("/ping")
                .then()
                .statusCode(200)
                .body(is("Ping Pong"));
    }

    @Test
    public void testAppInfo() {
        given()
                .when()
                .get("/app-info")
                .then()
                .statusCode(200);
    }
}

package io.vteial.workbench.controllers;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.vteial.workbench.models.Product;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
//@TestHTTPEndpoint(ProductController.class)
public class ProductControllerTest {

    String pathPrefix = "/api/products";

    @Test
    void initList() {
        List<Product> persons = given()
                .when()
                .get(pathPrefix)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath().getList(".", Product.class);
        assertEquals(persons.size(), 0);
    }

    @Test
    void addOne() {
        Product item = Product.builder()
                .code("inr")
                .name("Indian Rupee")
                .unit(1)
                .rate(1)
                .build();
        item = given()
                .body(item)
                .contentType(ContentType.JSON)
                .when()
                .post("/persons")
                .then()
                .statusCode(201)
                .extract()
                .body().as(Product.class);
        assertNotNull(item);
        assertNotNull(item.getId());
    }

    @Test
    void findById() {
        Product item = given()
                .pathParam("id", 1)
                .when()
                .get(pathPrefix + "/{id}")
                .then()
                .statusCode(204)
                .extract()
                .body().as(Product.class);
        assertNotNull(item);
        assertEquals(item.getId(), 1L);
    }
}

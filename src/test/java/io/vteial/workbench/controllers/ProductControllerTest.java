package io.vteial.workbench.controllers;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.vteial.workbench.models.Product;
import org.junit.jupiter.api.Order;
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
    @Order(1)
    void testList() {
        List<Product> items = given()
                .when()
                .get(pathPrefix)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath().getList(".", Product.class);
        assertEquals(0, items.size());
    }
/*
    @Test
    @Order(2)
    void testAdd() {
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
                .post(pathPrefix)
                .then()
                .statusCode(201)
                .extract()
                .body().as(Product.class);
        assertNotNull(item);
        assertNotNull(item.getId());
    }
    @Test
    @Order(3)
    void testFindById() {
        Product item = given()
                .pathParam("id", 1)
                .when()
                .get(pathPrefix + "/{id}")
                .then()
                .statusCode(200)
                .extract()
                .body().as(Product.class);
        assertNotNull(item);
        assertEquals(item.getId(), 1L);
    }

    @Test
    @Order(4)
    void testSet() {
        Product eitem = Product.builder()
                .id(1L)
                .code("inr")
                .name("Indian RupeeX")
                .unit(1)
                .rate(1)
                .build();
        Product aitem = given()
                .body(eitem)
                .pathParam("id", eitem.getId())
                .when()
                .put(pathPrefix + "/{id}")
                .then()
                .statusCode(204)
                .extract()
                .body().as(Product.class);
        assertNotNull(aitem);
        assertEquals(aitem.getCode(), eitem.getCode());
        assertEquals(aitem.getName(), eitem.getName());
        assertEquals(aitem.getUnit(), eitem.getUnit());
        assertEquals(aitem.getRate(), eitem.getRate());
    }

    @Test
    @Order(5)
    void testRemove() {
        given()
            .pathParam("id", 1)
            .when()
            .delete(pathPrefix + "/{id}")
            .then()
            .statusCode(204);
    }
*/
}

package io.vteial.workbench.controllers;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.callback.QuarkusTestBeforeEachCallback;
import io.quarkus.test.junit.callback.QuarkusTestMethodContext;
import io.restassured.http.ContentType;
import io.vteial.workbench.models.Product;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// @QuarkusTestResource(OidcWiremockTestResource.class)
// @QuarkusTestResource(KeycloakTestResourceLifecycleManager.class)
@QuarkusTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductControllerTest implements QuarkusTestBeforeEachCallback {

    String pathPrefix = "/api/products";

//    KeycloakTestClient keycloakClient = new KeycloakTestClient();
//
//    protected String getAccessToken(String userName) {
//        return keycloakClient.getAccessToken(userName);
//    }
//

    @Override
    public void beforeEach(QuarkusTestMethodContext context) {
        System.out.println("Executing " + context.getTestMethod());
        Annotation[] annotations = context.getTestInstance().getClass().getAnnotations();
        Arrays.stream(annotations).forEach(System.out::println);
    }

    @Test
    @Order(1)
    void testList() {
        List<Product> items = given()
//                .auth().oauth2(getAccessToken("alice"))
                .when()
                .get(pathPrefix)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath().getList(".", Product.class);
        assertEquals(0, items.size());
    }

    @Test
    @Order(2)
    void testFindByIdForNotFound() {
        given()
                .pathParam("id", 1)
                .when()
                .get(pathPrefix + "/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    @Order(3)
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
    @Order(4)
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
    @Order(5)
    void testUpdateForNotFound() {
        Product eitem = Product.builder()
                .id(10L)
                .code("inr")
                .name("Indian RupeeX")
                .unit(1)
                .rate(1)
                .build();
        given()
                .body(eitem)
                .contentType(ContentType.JSON)
                .pathParam("id", eitem.getId())
                .when()
                .put(pathPrefix + "/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    @Order(6)
    void testUpdate() {
        Product eitem = Product.builder()
                .id(1L)
                .code("inr")
                .name("Indian RupeeX")
                .unit(1)
                .rate(1)
                .build();
        Product aitem = given()
                .body(eitem)
                .contentType(ContentType.JSON)
                .pathParam("id", eitem.getId())
                .when()
                .put(pathPrefix + "/{id}")
                .then()
                .statusCode(200)
                .extract()
                .body().as(Product.class);
        assertNotNull(aitem);
        assertEquals(aitem.getCode(), eitem.getCode());
        assertEquals(aitem.getName(), eitem.getName());
        assertEquals(aitem.getUnit(), eitem.getUnit());
        assertEquals(aitem.getRate(), eitem.getRate());
    }

    @Test
    @Order(7)
    void testRemove() {
        given()
                .pathParam("id", 1)
                .when()
                .delete(pathPrefix + "/{id}")
                .then()
                .statusCode(204);
    }

    @Test
    @Order(8)
    void testRemoveForNotFound() {
        given()
                .pathParam("id", 1)
                .when()
                .delete(pathPrefix + "/{id}")
                .then()
                .statusCode(404);
    }
}

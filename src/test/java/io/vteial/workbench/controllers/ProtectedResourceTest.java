package io.vteial.workbench.controllers;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.security.oidc.Claim;
import io.quarkus.test.security.oidc.ConfigMetadata;
import io.quarkus.test.security.oidc.OidcSecurity;
import io.quarkus.test.security.oidc.UserInfo;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;

@QuarkusTest
@TestHTTPEndpoint(ProtectedResource.class)
public class ProtectedResourceTest {

    @Test
    @TestSecurity(user = "userOidc", roles = "viewer")
    public void testOidc() {
        RestAssured.when().get("test-security-oidc").then()
                .body(is("userOidc:viewer"));
    }

    @Test
    @TestSecurity(user = "userOidc", roles = "viewer")
    @OidcSecurity(claims = {
            @Claim(key = "email", value = "user@gmail.com")
    }, userinfo = {
            @UserInfo(key = "sub", value = "subject")
    }, config = {
            @ConfigMetadata(key = "issuer", value = "issuer")
    })
    public void testOidcWithClaimsUserInfoAndMetadata() {
        RestAssured.when().get("test-security-oidc-claims-userinfo-metadata").then()
                .body(is("userOidc:viewer:user@gmail.com:subject:issuer"));
    }

}

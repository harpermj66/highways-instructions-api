package com.vrs.v1.resource

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class UserResourceTest {

    @Test
    fun testUsersEndpoint() {
        given()
          .`when`().get("/users")
          .then()
             .statusCode(200)
             .body(`is`("[]"))
    }

    @Test
    fun testUserNotFound() {
        given()
          .`when`().get("/users/999")
          .then()
             .statusCode(404)
    }
}

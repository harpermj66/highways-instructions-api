package com.vrs.v1.resource

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.Test

@QuarkusTest
class RoleResourceTest {

    @Test
    fun testAddRoleAndList() {
        // Initial state
        given()
          .`when`().get("/roles")
          .then()
             .statusCode(200)

        val role = mapOf("name" to "Admin", "description" to "Administrator role")
        given()
          .contentType("application/json")
          .body(role)
          .`when`().post("/roles")
          .then()
             .statusCode(201)
             .body("name", `is`("Admin"))
             .body("description", `is`("Administrator role"))

        // After adding
        given()
          .`when`().get("/roles")
          .then()
             .statusCode(200)
             .body("name", org.hamcrest.Matchers.hasItem("Admin"))

        val id = given()
          .`when`().get("/roles")
          .then()
             .statusCode(200)
             .extract().path<Int>("find { it.name == 'Admin' }.id")

        given()
          .`when`().delete("/roles/$id")
          .then()
             .statusCode(204)

        given()
          .`when`().get("/roles")
          .then()
             .statusCode(200)
             .body("name", org.hamcrest.Matchers.not(org.hamcrest.Matchers.hasItem("Admin")))
    }
}

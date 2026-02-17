package com.vrs.v1.integration.resource

import com.vrs.v1.resource.*

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class UserLifecycleTest {

    @Test
    fun testUserLifecycle() {
        val username = "testuser"
        val email = "test@example.com"

        // 0. Create two roles
        val role1Id = given()
            .contentType("application/json")
            .body(mapOf("name" to "Role1", "description" to "First role"))
            .`when`().post("/roles")
            .then()
            .statusCode(201)
            .extract().path<Int>("id")

        val role2Id = given()
            .contentType("application/json")
            .body(mapOf("name" to "Role2", "description" to "Second role"))
            .`when`().post("/roles")
            .then()
            .statusCode(201)
            .extract().path<Int>("id")

        // 1. Create a new user with two roles
        val userId = given()
            .contentType("application/json")
            .body(mapOf(
                "username" to username,
                "email" to email,
                "roles" to listOf(mapOf("id" to role1Id), mapOf("id" to role2Id))
            ))
            .`when`().post("/users")
            .then()
            .statusCode(201)
            .body("username", `is`(username))
            .body("email", `is`(email))
            .body("roles.size()", `is`(2))
            .extract().path<Int>("id")

        // 2. Verify the new user exists and has roles
        given()
            .`when`().get("/users")
            .then()
            .statusCode(200)
            .body("find { it.id == $userId }.username", `is`(username))
            .body("find { it.id == $userId }.email", `is`(email))
            .body("find { it.id == $userId }.roles.size()", `is`(2))

        // 3. Delete the new user
        given()
            .`when`().delete("/users/$userId")
            .then()
            .statusCode(204)

        // 4. Verify the user is deleted
        given()
            .`when`().get("/users")
            .then()
            .statusCode(200)
            .body("id", org.hamcrest.Matchers.not(org.hamcrest.Matchers.hasItem(userId)))

        // 5. Delete roles
        given()
            .`when`().delete("/roles/$role1Id")
            .then()
            .statusCode(204)
        given()
            .`when`().delete("/roles/$role2Id")
            .then()
            .statusCode(204)

        // 6. Verify roles are deleted
        given()
            .`when`().get("/roles")
            .then()
            .statusCode(200)
            .body("id", org.hamcrest.Matchers.not(org.hamcrest.Matchers.hasItem(role1Id)))
            .body("id", org.hamcrest.Matchers.not(org.hamcrest.Matchers.hasItem(role2Id)))
    }
}

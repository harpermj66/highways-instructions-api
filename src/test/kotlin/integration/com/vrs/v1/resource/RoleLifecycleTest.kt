package com.vrs.v1.resource

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class RoleLifecycleTest {

    @Test
    fun testRoleLifecycle() {
        val roleName = "TestRole"
        val roleDescription = "A temporary role for testing"

        // 1. Create a new role
        val roleId = given()
            .contentType("application/json")
            .body(mapOf("name" to roleName, "description" to roleDescription))
            .`when`().post("/roles")
            .then()
            .statusCode(201)
            .body("name", `is`(roleName))
            .body("description", `is`(roleDescription))
            .extract().path<Int>("id")

        // 2. Verify the new role exists
        given()
            .`when`().get("/roles")
            .then()
            .statusCode(200)
            .body("find { it.id == $roleId }.name", `is`(roleName))
            .body("find { it.id == $roleId }.description", `is`(roleDescription))

        // 3. Delete the new role
        given()
            .`when`().delete("/roles/$roleId")
            .then()
            .statusCode(204)

        // 4. Verify the role is deleted
        given()
            .`when`().get("/roles")
            .then()
            .statusCode(200)
            .body("id", org.hamcrest.Matchers.not(org.hamcrest.Matchers.hasItem(roleId)))
    }
}

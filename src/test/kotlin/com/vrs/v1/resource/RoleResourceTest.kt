package com.vrs.v1.resource

import com.vrs.v1.entity.Role
import com.vrs.v1.service.RoleService
import io.quarkus.test.InjectMock
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.smallrye.mutiny.Uni
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@QuarkusTest
class RoleResourceTest {

    @InjectMock
    lateinit var service: RoleService

    @Test
    fun testListAll() {
        whenever(service.listAll()).thenReturn(Uni.createFrom().item(listOf()))

        given()
            .`when`().get("/roles")
            .then()
            .statusCode(200)
            .body(`is`("[]"))
    }

    @Test
    fun testAdd() {
        val item = Role().apply { 
            id = 1L
        }
        whenever(service.add(any())).thenReturn(Uni.createFrom().item(item))

        given()
            .contentType("application/json")
            .body(item)
            .`when`().post("/roles")
            .then()
            .statusCode(201)
            .body("id", `is`(1))
    }

    @Test
    fun testDelete() {
        whenever(service.deleteById(1L)).thenReturn(Uni.createFrom().item(true))

        given()
            .`when`().delete("/roles/1")
            .then()
            .statusCode(204)
    }

    @Test
    fun testDeleteNotFound() {
        whenever(service.deleteById(999L)).thenReturn(Uni.createFrom().item(false))

        given()
            .`when`().delete("/roles/999")
            .then()
            .statusCode(404)
    }
}

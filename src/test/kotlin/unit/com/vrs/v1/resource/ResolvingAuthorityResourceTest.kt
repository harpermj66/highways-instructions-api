package com.vrs.v1.resource

import com.vrs.v1.entity.ResolvingAuthority
import com.vrs.v1.service.ResolvingAuthorityService
import io.quarkus.test.InjectMock
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.smallrye.mutiny.Uni
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@QuarkusTest
class ResolvingAuthorityResourceTest {

    @InjectMock
    lateinit var service: ResolvingAuthorityService

    @Test
    fun testListAll() {
        whenever(service.listAll()).thenReturn(Uni.createFrom().item(listOf()))

        given()
            .`when`().get("/resolving-authoritys")
            .then()
            .statusCode(200)
            .body(`is`("[]"))
    }
    @Test
    fun testGetById() {
        val item = ResolvingAuthority().apply { 
            resolving_authority_id = 1L
            created_by = "test"
        }
        whenever(service.findById(1L)).thenReturn(Uni.createFrom().item(item))

        given()
            .`when`().get("/resolving-authoritys/1")
            .then()
            .statusCode(200)
            .body("resolving_authority_id", `is`(1))
    }

    @Test
    fun testGetByIdNotFound() {
        whenever(service.findById(999L)).thenReturn(Uni.createFrom().nullItem())

        given()
            .`when`().get("/resolving-authoritys/999")
            .then()
            .statusCode(404)
    }

    @Test
    fun testAdd() {
        val item = ResolvingAuthority().apply { 
            resolving_authority_id = 1L
            created_by = "test"
        }
        whenever(service.add(any())).thenReturn(Uni.createFrom().item(item))

        given()
            .contentType("application/json")
            .body(item)
            .`when`().post("/resolving-authoritys")
            .then()
            .statusCode(201)
            .body("resolving_authority_id", `is`(1))
    }

    @Test
    fun testDelete() {
        whenever(service.deleteById(1L)).thenReturn(Uni.createFrom().item(true))

        given()
            .`when`().delete("/resolving-authoritys/1")
            .then()
            .statusCode(204)
    }

    @Test
    fun testDeleteNotFound() {
        whenever(service.deleteById(999L)).thenReturn(Uni.createFrom().item(false))

        given()
            .`when`().delete("/resolving-authoritys/999")
            .then()
            .statusCode(404)
    }
}

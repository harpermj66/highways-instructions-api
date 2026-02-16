package com.vrs.v1.resource

import com.vrs.v1.entity.GangAssignment
import com.vrs.v1.service.GangAssignmentService
import io.quarkus.test.InjectMock
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.smallrye.mutiny.Uni
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@QuarkusTest
class GangAssignmentResourceTest {

    @InjectMock
    lateinit var service: GangAssignmentService

    @Test
    fun testListAll() {
        whenever(service.listAll()).thenReturn(Uni.createFrom().item(listOf()))

        given()
            .`when`().get("/gang-assignments")
            .then()
            .statusCode(200)
            .body(`is`("[]"))
    }
    @Test
    fun testGetById() {
        val item = GangAssignment().apply { 
            gang_assignment_id = 1L
        }
        whenever(service.findById(1L)).thenReturn(Uni.createFrom().item(item))

        given()
            .`when`().get("/gang-assignments/1")
            .then()
            .statusCode(200)
            .body("gang_assignment_id", `is`(1))
    }

    @Test
    fun testGetByIdNotFound() {
        whenever(service.findById(999L)).thenReturn(Uni.createFrom().nullItem())

        given()
            .`when`().get("/gang-assignments/999")
            .then()
            .statusCode(404)
    }

    @Test
    fun testAdd() {
        val item = GangAssignment().apply { 
            gang_assignment_id = 1L
        }
        whenever(service.add(any())).thenReturn(Uni.createFrom().item(item))

        given()
            .contentType("application/json")
            .body(item)
            .`when`().post("/gang-assignments")
            .then()
            .statusCode(201)
            .body("gang_assignment_id", `is`(1))
    }

    @Test
    fun testDelete() {
        whenever(service.deleteById(1L)).thenReturn(Uni.createFrom().item(true))

        given()
            .`when`().delete("/gang-assignments/1")
            .then()
            .statusCode(204)
    }

    @Test
    fun testDeleteNotFound() {
        whenever(service.deleteById(999L)).thenReturn(Uni.createFrom().item(false))

        given()
            .`when`().delete("/gang-assignments/999")
            .then()
            .statusCode(404)
    }
}

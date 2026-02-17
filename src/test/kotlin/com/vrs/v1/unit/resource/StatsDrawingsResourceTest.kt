package com.vrs.v1.unit.resource

import com.vrs.v1.resource.*

import com.vrs.v1.entity.StatsDrawings
import com.vrs.v1.service.StatsDrawingsService
import io.quarkus.test.InjectMock
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.smallrye.mutiny.Uni
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@QuarkusTest
class StatsDrawingsResourceTest {

    @InjectMock
    lateinit var service: StatsDrawingsService

    @Test
    fun testListAll() {
        whenever(service.listAll()).thenReturn(Uni.createFrom().item(listOf()))

        given()
            .`when`().get("/stats-drawingss")
            .then()
            .statusCode(200)
            .body(`is`("[]"))
    }
    @Test
    fun testGetById() {
        val item = StatsDrawings().apply { 
            stats_drawings_id = 1L
            created_by = "test"
        }
        whenever(service.findById(1L)).thenReturn(Uni.createFrom().item(item))

        given()
            .`when`().get("/stats-drawingss/1")
            .then()
            .statusCode(200)
            .body("stats_drawings_id", `is`(1))
    }

    @Test
    fun testGetByIdNotFound() {
        whenever(service.findById(999L)).thenReturn(Uni.createFrom().nullItem())

        given()
            .`when`().get("/stats-drawingss/999")
            .then()
            .statusCode(404)
    }

    @Test
    fun testAdd() {
        val item = StatsDrawings().apply { 
            stats_drawings_id = 1L
            created_by = "test"
        }
        whenever(service.add(any())).thenReturn(Uni.createFrom().item(item))

        given()
            .contentType("application/json")
            .body(item)
            .`when`().post("/stats-drawingss")
            .then()
            .statusCode(201)
            .body("stats_drawings_id", `is`(1))
    }

    @Test
    fun testDelete() {
        whenever(service.deleteById(1L)).thenReturn(Uni.createFrom().item(true))

        given()
            .`when`().delete("/stats-drawingss/1")
            .then()
            .statusCode(204)
    }

    @Test
    fun testDeleteNotFound() {
        whenever(service.deleteById(999L)).thenReturn(Uni.createFrom().item(false))

        given()
            .`when`().delete("/stats-drawingss/999")
            .then()
            .statusCode(404)
    }
}

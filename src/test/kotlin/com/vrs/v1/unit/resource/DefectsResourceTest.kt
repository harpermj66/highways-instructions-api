package com.vrs.v1.unit.resource

import com.vrs.v1.resource.*

import com.vrs.v1.entity.Defects
import com.vrs.v1.service.DefectsService
import io.quarkus.test.InjectMock
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.smallrye.mutiny.Uni
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@QuarkusTest
class DefectsResourceTest {

    @InjectMock
    lateinit var service: DefectsService

    @Test
    fun testListAll() {
        whenever(service.listAll()).thenReturn(Uni.createFrom().item(listOf()))

        given()
            .`when`().get("/defectss")
            .then()
            .statusCode(200)
            .body(`is`("[]"))
    }
    @Test
    fun testGetById() {
        val item = Defects().apply { 
            defects_id = 1L
            defect_code = "test"
            created_by = "test"
        }
        whenever(service.findById(1L)).thenReturn(Uni.createFrom().item(item))

        given()
            .`when`().get("/defectss/1")
            .then()
            .statusCode(200)
            .body("defects_id", `is`(1))
    }

    @Test
    fun testGetByIdNotFound() {
        whenever(service.findById(999L)).thenReturn(Uni.createFrom().nullItem())

        given()
            .`when`().get("/defectss/999")
            .then()
            .statusCode(404)
    }

    @Test
    fun testAdd() {
        val item = Defects().apply { 
            defects_id = 1L
            defect_code = "test"
            created_by = "test"
        }
        whenever(service.add(any())).thenReturn(Uni.createFrom().item(item))

        given()
            .contentType("application/json")
            .body(item)
            .`when`().post("/defectss")
            .then()
            .statusCode(201)
            .body("defects_id", `is`(1))
    }

    @Test
    fun testDelete() {
        whenever(service.deleteById(1L)).thenReturn(Uni.createFrom().item(true))

        given()
            .`when`().delete("/defectss/1")
            .then()
            .statusCode(204)
    }

    @Test
    fun testDeleteNotFound() {
        whenever(service.deleteById(999L)).thenReturn(Uni.createFrom().item(false))

        given()
            .`when`().delete("/defectss/999")
            .then()
            .statusCode(404)
    }
}

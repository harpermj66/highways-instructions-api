package com.vrs.v1.unit.resource

import com.vrs.v1.resource.*

import com.vrs.v1.entity.JobInstruction
import com.vrs.v1.service.JobInstructionService
import io.quarkus.test.InjectMock
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.smallrye.mutiny.Uni
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@QuarkusTest
class JobInstructionResourceTest {

    @InjectMock
    lateinit var service: JobInstructionService

    @Test
    fun testListAll() {
        whenever(service.listAll()).thenReturn(Uni.createFrom().item(listOf()))

        given()
            .`when`().get("/job-instructions")
            .then()
            .statusCode(200)
            .body(`is`("[]"))
    }
    @Test
    fun testGetById() {
        val item = JobInstruction().apply { 
            job_instruction_id = 1L
            created_by = "test"
            confirm_enquiry = "test"
            depot = "test"
        }
        whenever(service.findById(1L)).thenReturn(Uni.createFrom().item(item))

        given()
            .`when`().get("/job-instructions/1")
            .then()
            .statusCode(200)
            .body("job_instruction_id", `is`(1))
    }

    @Test
    fun testGetByIdNotFound() {
        whenever(service.findById(999L)).thenReturn(Uni.createFrom().nullItem())

        given()
            .`when`().get("/job-instructions/999")
            .then()
            .statusCode(404)
    }

    @Test
    fun testAdd() {
        val item = JobInstruction().apply { 
            job_instruction_id = 1L
            created_by = "test"
            confirm_enquiry = "test"
            depot = "test"
        }
        whenever(service.add(any())).thenReturn(Uni.createFrom().item(item))

        given()
            .contentType("application/json")
            .body(item)
            .`when`().post("/job-instructions")
            .then()
            .statusCode(201)
            .body("job_instruction_id", `is`(1))
    }

    @Test
    fun testDelete() {
        whenever(service.deleteById(1L)).thenReturn(Uni.createFrom().item(true))

        given()
            .`when`().delete("/job-instructions/1")
            .then()
            .statusCode(204)
    }

    @Test
    fun testDeleteNotFound() {
        whenever(service.deleteById(999L)).thenReturn(Uni.createFrom().item(false))

        given()
            .`when`().delete("/job-instructions/999")
            .then()
            .statusCode(404)
    }
}

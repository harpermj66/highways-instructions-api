package com.vrs.v1.service

import com.vrs.v1.entity.JobAssessment
import com.vrs.v1.repository.JobAssessmentRepository
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class JobAssessmentService(private val repository: JobAssessmentRepository) {

    @WithSession
    fun listAll(): Uni<List<JobAssessment>> = repository.listAll()

    @WithSession
    fun findById(id: Long): Uni<JobAssessment> = repository.findById(id)

    @WithTransaction
    fun add(entity: JobAssessment): Uni<JobAssessment> {
        return repository.persist(entity)
    }

    @WithTransaction
    fun deleteById(id: Long): Uni<Boolean> {
        return repository.deleteById(id)
    }
}

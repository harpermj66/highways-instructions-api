package com.vrs.v1.service

import com.vrs.v1.entity.JobAssessmentContraints
import com.vrs.v1.repository.JobAssessmentContraintsRepository
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class JobAssessmentContraintsService(private val repository: JobAssessmentContraintsRepository) {

    @WithSession
    fun listAll(): Uni<List<JobAssessmentContraints>> = repository.listAll()

    @WithSession
    fun findById(id: Long): Uni<JobAssessmentContraints> = repository.findById(id)

    @WithTransaction
    fun add(entity: JobAssessmentContraints): Uni<JobAssessmentContraints> {
        return repository.persist(entity)
    }

    @WithTransaction
    fun deleteById(id: Long): Uni<Boolean> {
        return repository.deleteById(id)
    }
}

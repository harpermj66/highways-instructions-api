package com.vrs.v1.service

import com.vrs.v1.entity.JobInstruction
import com.vrs.v1.repository.JobInstructionRepository
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class JobInstructionService(private val repository: JobInstructionRepository) {

    @WithSession
    fun listAll(): Uni<List<JobInstruction>> = repository.listAll()

    @WithSession
    fun findById(id: Long): Uni<JobInstruction> = repository.findById(id)

    @WithTransaction
    fun add(entity: JobInstruction): Uni<JobInstruction> {
        return repository.persist(entity)
    }

    @WithTransaction
    fun deleteById(id: Long): Uni<Boolean> {
        return repository.deleteById(id)
    }
}

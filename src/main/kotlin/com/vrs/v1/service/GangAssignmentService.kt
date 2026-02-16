package com.vrs.v1.service

import com.vrs.v1.entity.GangAssignment
import com.vrs.v1.repository.GangAssignmentRepository
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class GangAssignmentService(private val repository: GangAssignmentRepository) {

    @WithSession
    fun listAll(): Uni<List<GangAssignment>> = repository.listAll()

    @WithSession
    fun findById(id: Long): Uni<GangAssignment> = repository.findById(id)

    @WithTransaction
    fun add(entity: GangAssignment): Uni<GangAssignment> {
        return repository.persist(entity)
    }

    @WithTransaction
    fun deleteById(id: Long): Uni<Boolean> {
        return repository.deleteById(id)
    }
}

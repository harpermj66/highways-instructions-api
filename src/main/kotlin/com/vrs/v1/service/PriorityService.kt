package com.vrs.v1.service

import com.vrs.v1.entity.Priority
import com.vrs.v1.repository.PriorityRepository
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class PriorityService(private val repository: PriorityRepository) {

    @WithSession
    fun listAll(): Uni<List<Priority>> = repository.listAll()

    @WithSession
    fun findById(id: Long): Uni<Priority> = repository.findById(id)

    @WithTransaction
    fun add(entity: Priority): Uni<Priority> {
        return repository.persist(entity)
    }

    @WithTransaction
    fun deleteById(id: Long): Uni<Boolean> {
        return repository.deleteById(id)
    }
}

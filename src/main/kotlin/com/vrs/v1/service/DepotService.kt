package com.vrs.v1.service

import com.vrs.v1.entity.Depot
import com.vrs.v1.repository.DepotRepository
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class DepotService(private val repository: DepotRepository) {

    @WithSession
    fun listAll(): Uni<List<Depot>> = repository.listAll()

    @WithSession
    fun findById(id: Long): Uni<Depot> = repository.findById(id)

    @WithTransaction
    fun add(entity: Depot): Uni<Depot> {
        return repository.persist(entity)
    }

    @WithTransaction
    fun deleteById(id: Long): Uni<Boolean> {
        return repository.deleteById(id)
    }
}

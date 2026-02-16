package com.vrs.v1.service

import com.vrs.v1.entity.SorItems
import com.vrs.v1.repository.SorItemsRepository
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class SorItemsService(private val repository: SorItemsRepository) {

    @WithSession
    fun listAll(): Uni<List<SorItems>> = repository.listAll()

    @WithSession
    fun findById(id: Long): Uni<SorItems> = repository.findById(id)

    @WithTransaction
    fun add(entity: SorItems): Uni<SorItems> {
        return repository.persist(entity)
    }

    @WithTransaction
    fun deleteById(id: Long): Uni<Boolean> {
        return repository.deleteById(id)
    }
}

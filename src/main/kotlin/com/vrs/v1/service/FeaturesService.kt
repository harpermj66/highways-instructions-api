package com.vrs.v1.service

import com.vrs.v1.entity.Features
import com.vrs.v1.repository.FeaturesRepository
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class FeaturesService(private val repository: FeaturesRepository) {

    @WithSession
    fun listAll(): Uni<List<Features>> = repository.listAll()

    @WithSession
    fun findById(id: Long): Uni<Features> = repository.findById(id)

    @WithTransaction
    fun add(entity: Features): Uni<Features> {
        return repository.persist(entity)
    }

    @WithTransaction
    fun deleteById(id: Long): Uni<Boolean> {
        return repository.deleteById(id)
    }
}

package com.vrs.v1.service

import com.vrs.v1.entity.Officers
import com.vrs.v1.repository.OfficersRepository
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class OfficersService(private val repository: OfficersRepository) {

    @WithSession
    fun listAll(): Uni<List<Officers>> = repository.listAll()

    @WithSession
    fun findById(id: Long): Uni<Officers> = repository.findById(id)

    @WithTransaction
    fun add(entity: Officers): Uni<Officers> {
        return repository.persist(entity)
    }

    @WithTransaction
    fun deleteById(id: Long): Uni<Boolean> {
        return repository.deleteById(id)
    }
}

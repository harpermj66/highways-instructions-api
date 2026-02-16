package com.vrs.v1.service

import com.vrs.v1.entity.StatsDrawings
import com.vrs.v1.repository.StatsDrawingsRepository
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class StatsDrawingsService(private val repository: StatsDrawingsRepository) {

    @WithSession
    fun listAll(): Uni<List<StatsDrawings>> = repository.listAll()

    @WithSession
    fun findById(id: Long): Uni<StatsDrawings> = repository.findById(id)

    @WithTransaction
    fun add(entity: StatsDrawings): Uni<StatsDrawings> {
        return repository.persist(entity)
    }

    @WithTransaction
    fun deleteById(id: Long): Uni<Boolean> {
        return repository.deleteById(id)
    }
}

package com.vrs.v1.service

import com.vrs.v1.entity.CompetencyOverride
import com.vrs.v1.repository.CompetencyOverrideRepository
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class CompetencyOverrideService(private val repository: CompetencyOverrideRepository) {

    @WithSession
    fun listAll(): Uni<List<CompetencyOverride>> = repository.listAll()

    @WithSession
    fun findById(id: Long): Uni<CompetencyOverride> = repository.findById(id)

    @WithTransaction
    fun add(entity: CompetencyOverride): Uni<CompetencyOverride> {
        return repository.persist(entity)
    }

    @WithTransaction
    fun deleteById(id: Long): Uni<Boolean> {
        return repository.deleteById(id)
    }
}

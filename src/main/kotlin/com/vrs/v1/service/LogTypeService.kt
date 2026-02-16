package com.vrs.v1.service

import com.vrs.v1.entity.LogType
import com.vrs.v1.repository.LogTypeRepository
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class LogTypeService(private val repository: LogTypeRepository) {

    @WithSession
    fun listAll(): Uni<List<LogType>> = repository.listAll()

    @WithSession
    fun findById(id: Long): Uni<LogType> = repository.findById(id)

    @WithTransaction
    fun add(entity: LogType): Uni<LogType> {
        return repository.persist(entity)
    }

    @WithTransaction
    fun deleteById(id: Long): Uni<Boolean> {
        return repository.deleteById(id)
    }
}

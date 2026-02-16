package com.vrs.v1.service

import com.vrs.v1.entity.ActionLog
import com.vrs.v1.repository.ActionLogRepository
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class ActionLogService(private val repository: ActionLogRepository) {

    @WithSession
    fun listAll(): Uni<List<ActionLog>> = repository.listAll()

    @WithSession
    fun findById(id: Long): Uni<ActionLog> = repository.findById(id)

    @WithTransaction
    fun add(entity: ActionLog): Uni<ActionLog> {
        return repository.persist(entity)
    }

    @WithTransaction
    fun deleteById(id: Long): Uni<Boolean> {
        return repository.deleteById(id)
    }
}

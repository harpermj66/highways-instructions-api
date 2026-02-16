package com.vrs.v1.service

import com.vrs.v1.entity.ScheduleOfRates
import com.vrs.v1.repository.ScheduleOfRatesRepository
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class ScheduleOfRatesService(private val repository: ScheduleOfRatesRepository) {

    @WithSession
    fun listAll(): Uni<List<ScheduleOfRates>> = repository.listAll()

    @WithSession
    fun findById(id: Long): Uni<ScheduleOfRates> = repository.findById(id)

    @WithTransaction
    fun add(entity: ScheduleOfRates): Uni<ScheduleOfRates> {
        return repository.persist(entity)
    }

    @WithTransaction
    fun deleteById(id: Long): Uni<Boolean> {
        return repository.deleteById(id)
    }
}

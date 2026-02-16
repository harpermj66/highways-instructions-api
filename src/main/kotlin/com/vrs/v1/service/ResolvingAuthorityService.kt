package com.vrs.v1.service

import com.vrs.v1.entity.ResolvingAuthority
import com.vrs.v1.repository.ResolvingAuthorityRepository
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class ResolvingAuthorityService(private val repository: ResolvingAuthorityRepository) {

    @WithSession
    fun listAll(): Uni<List<ResolvingAuthority>> = repository.listAll()

    @WithSession
    fun findById(id: Long): Uni<ResolvingAuthority> = repository.findById(id)

    @WithTransaction
    fun add(entity: ResolvingAuthority): Uni<ResolvingAuthority> {
        return repository.persist(entity)
    }

    @WithTransaction
    fun deleteById(id: Long): Uni<Boolean> {
        return repository.deleteById(id)
    }
}

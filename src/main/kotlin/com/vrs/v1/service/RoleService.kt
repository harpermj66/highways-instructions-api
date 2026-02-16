package com.vrs.v1.service

import com.vrs.v1.entity.Role
import com.vrs.v1.repository.RoleRepository
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class RoleService(private val roleRepository: RoleRepository) {

    @WithSession
    fun listAll(): Uni<List<Role>> = roleRepository.listAll()

    @WithTransaction
    fun add(role: Role): Uni<Role> {
        return roleRepository.persist(role)
    }

    @WithTransaction
    fun deleteById(id: Long): Uni<Boolean> {
        return roleRepository.deleteById(id)
    }
}

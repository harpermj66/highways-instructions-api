package com.vrs.v1.service

import com.vrs.v1.entity.User
import com.vrs.v1.repository.UserRepository
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class UserService(private val userRepository: UserRepository) {

    @WithSession
    fun listAll(): Uni<List<User>> = userRepository.listAll()

    @WithSession
    fun findById(id: Long): Uni<User> = userRepository.findById(id)

    @WithTransaction
    fun add(user: User): Uni<User> {
        return userRepository.persist(user)
    }

    @WithTransaction
    fun deleteById(id: Long): Uni<Boolean> {
        return userRepository.deleteById(id)
    }
}

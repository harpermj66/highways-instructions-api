package com.vrs.v1.repository

import com.vrs.v1.entity.LogType
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class LogTypeRepository : PanacheRepository<LogType>

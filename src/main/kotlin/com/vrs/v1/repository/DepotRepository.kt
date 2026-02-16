package com.vrs.v1.repository

import com.vrs.v1.entity.Depot
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class DepotRepository : PanacheRepository<Depot>

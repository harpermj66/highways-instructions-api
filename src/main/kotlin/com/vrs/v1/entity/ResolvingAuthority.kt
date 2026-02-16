package com.vrs.v1.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.LocalDate

@Entity
@Table(name = "Resolving_Authority")
class ResolvingAuthority {
    @Id
    @Column(name = "RESOLVING_AUTHORITY_ID", nullable = false, precision = 10)
    var resolving_authority_id: Long? = null

    @Column(name = "RESOLVING_AUTHORITY")
    var resolving_authority: String? = null

    @Column(name = "DATE_CREATED", nullable = false)
    var date_created: java.time.LocalDate? = null

    @Column(name = "CREATED_BY", nullable = false)
    lateinit var created_by: String

}

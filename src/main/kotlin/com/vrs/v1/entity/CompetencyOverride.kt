package com.vrs.v1.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.LocalDate

@Entity
@Table(name = "Competency_Override")
class CompetencyOverride {
    @Id
    @Column(name = "COMPETENCY_OVERRIDE_ID", nullable = false, precision = 10)
    var competency_override_id: Long? = null

    @Column(name = "DESCRIPTION", nullable = false)
    lateinit var description: String

    @Column(name = "DATE_CREATED", nullable = false)
    var date_created: java.time.LocalDate? = null

    @Column(name = "CREATED_BY", nullable = false)
    lateinit var created_by: String

}

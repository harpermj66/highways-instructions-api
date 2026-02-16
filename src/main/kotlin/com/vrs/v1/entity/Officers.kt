package com.vrs.v1.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.LocalDate

@Entity
@Table(name = "Officers")
class Officers {
    @Id
    @Column(name = "OFFICERS_ID", nullable = false, precision = 10)
    var officers_id: Long? = null

    @Column(name = "OFFICER")
    var officer: String? = null

    @Column(name = "DATE_CREATED", nullable = false)
    var date_created: java.time.LocalDate? = null

    @Column(name = "CREATED_BY", nullable = false)
    lateinit var created_by: String

}

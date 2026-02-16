package com.vrs.v1.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.LocalDate

@Entity
@Table(name = "Priority")
class Priority {
    @Id
    @Column(name = "PRIORITY_ID", nullable = false, precision = 10)
    var priority_id: Long? = null

    @Column(name = "PRIORITY_CODE", nullable = false)
    lateinit var priority_code: String

    @Column(name = "PRIORITY", nullable = false)
    lateinit var priority: String

    @Column(name = "DAYS", precision = 3)
    var days: Int? = null

    @Column(name = "HOURS", precision = 4, scale = 2)
    var hours: java.math.BigDecimal? = null

    @Column(name = "DATE_CREATED", nullable = false)
    var date_created: java.time.LocalDate? = null

    @Column(name = "CREATED_BY", nullable = false)
    lateinit var created_by: String

}

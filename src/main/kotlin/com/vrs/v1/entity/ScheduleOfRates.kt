package com.vrs.v1.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.LocalDate

@Entity
@Table(name = "schedule_of_rates")
class ScheduleOfRates {
    @Id
    @Column(name = "SOR_ID", nullable = false, precision = 10)
    var sor_id: Long? = null

    @Column(name = "SOR_DESCRIPTION")
    var sor_description: String? = null

    @Column(name = "SOR_CODE", nullable = false)
    lateinit var sor_code: String

    @Column(name = "UOM", nullable = false)
    lateinit var uom: String

    @Column(name = "RATE", nullable = false, precision = 10, scale = 4)
    var rate: java.math.BigDecimal? = null

    @Column(name = "SOR_DESCN")
    var sor_descn: String? = null

    @Column(name = "SOR_TYPE", nullable = false)
    lateinit var sor_type: String

    @Column(name = "DATE_CREATED", nullable = false)
    var date_created: java.time.LocalDate? = null

    @Column(name = "CREATED_BY", nullable = false)
    lateinit var created_by: String

}

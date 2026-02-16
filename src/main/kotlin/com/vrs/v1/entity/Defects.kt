package com.vrs.v1.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.LocalDate

@Entity
@Table(name = "Defects")
class Defects {
    @Id
    @Column(name = "DEFECTS_ID", nullable = false, precision = 10)
    var defects_id: Long? = null

    @Column(name = "FEATURE")
    var feature: String? = null

    @Column(name = "DEFECT_DESCRIPTION")
    var defect_description: String? = null

    @Column(name = "DEFECT_CODE", nullable = false)
    lateinit var defect_code: String

    @Column(name = "DATE_CREATED", nullable = false)
    var date_created: java.time.LocalDate? = null

    @Column(name = "CREATED_BY", nullable = false)
    lateinit var created_by: String

}

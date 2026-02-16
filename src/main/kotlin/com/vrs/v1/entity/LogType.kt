package com.vrs.v1.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.LocalDate

@Entity
@Table(name = "Log_Type")
class LogType {
    @Id
    @Column(name = "LOG_TYPE_ID", nullable = false, precision = 10)
    var log_type_id: Long? = null

    @Column(name = "LOG_TYPE", nullable = false)
    lateinit var log_type: String

    @Column(name = "DATE_CREATED", nullable = false)
    var date_created: java.time.LocalDate? = null

    @Column(name = "CREATED_BY", nullable = false)
    lateinit var created_by: String

}

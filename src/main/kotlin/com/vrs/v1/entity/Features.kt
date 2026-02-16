package com.vrs.v1.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.LocalDate

@Entity
@Table(name = "Features")
class Features {
    @Id
    @Column(name = "FEATURES_ID", nullable = false, precision = 10)
    var features_id: Long? = null

    @Column(name = "FEATURE_DESCRIPTION")
    var feature_description: String? = null

    @Column(name = "FEATURE_CODE")
    var feature_code: String? = null

    @Column(name = "DATE_CREATED", nullable = false)
    var date_created: java.time.LocalDate? = null

    @Column(name = "CREATED_BY", nullable = false)
    lateinit var created_by: String

}

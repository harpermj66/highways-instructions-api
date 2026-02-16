package com.vrs.v1.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.LocalDate

@Entity
@Table(name = "Stats_Drawings")
class StatsDrawings {
    @Id
    @Column(name = "STATS_DRAWINGS_ID", nullable = false, precision = 10)
    var stats_drawings_id: Long? = null

    @Column(name = "DUE_DATE")
    var due_date: java.time.LocalDate? = null

    @Column(name = "ESTIMATED_DURATION", nullable = false, precision = 6, scale = 2)
    var estimated_duration: java.math.BigDecimal? = null

    @Column(name = "MINUTES", nullable = false, precision = 4, scale = 2)
    var minutes: java.math.BigDecimal? = null

    @Column(name = "STATS_DRAWINGS_REQUIRED")
    var stats_drawings_required: String? = null

    @Column(name = "RECEIPT_ESTIMATE")
    var receipt_estimate: java.time.LocalDate? = null

    @Column(name = "DATE_DRAWINGS_RECEIVED")
    var date_drawings_received: java.time.LocalDate? = null

    @Column(name = "NONE")
    var none: String? = null

    @Column(name = "DAY_3_NOTICE")
    var day_3_notice: String? = null

    @Column(name = "EMERGENCY")
    var emergency: String? = null

    @Column(name = "DAY_7_NOTICE")
    var day_7_notice: String? = null

    @Column(name = "DAILY_WHEREABOUTS")
    var daily_whereabouts: String? = null

    @Column(name = "FIXED_JOB")
    var fixed_job: String? = null

    @Column(name = "DATE_CREATED", nullable = false)
    var date_created: java.time.LocalDate? = null

    @Column(name = "CREATED_BY", nullable = false)
    lateinit var created_by: String

    @OneToMany(mappedBy = "statsDrawings", cascade = [CascadeType.ALL])
    var jobAssessments: MutableList<JobAssessment> = mutableListOf()

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JI_ID", insertable = false, updatable = false)
    var jobInstruction: JobInstruction? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JI_ID", insertable = false, updatable = false)
    var incompleteJobInstructions: IncompleteJobInstructions? = null

}

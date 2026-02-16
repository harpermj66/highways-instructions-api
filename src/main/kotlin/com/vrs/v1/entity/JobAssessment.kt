package com.vrs.v1.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.LocalDate

@Entity
@Table(name = "Job_Assessment")
class JobAssessment {
    @Id
    @Column(name = "JOB_ASSESSMENT_ID", nullable = false, precision = 10)
    var job_assessment_id: Long? = null

    @Column(name = "TYPE", nullable = false)
    lateinit var type: String

    @Column(name = "CONSTRAINT")
    var constraint: String? = null

    @Column(name = "DESCRIPTION", nullable = false)
    lateinit var description: String

    @Column(name = "RULE_1")
    var rule_1: String? = null

    @Column(name = "RULE_2")
    var rule_2: String? = null

    @Column(name = "RULE_3")
    var rule_3: String? = null

    @Column(name = "RULE_4")
    var rule_4: String? = null

    @Column(name = "JOB_ASSESSMENT_TYPE_ID", precision = 10)
    var job_assessment_type_id: Long? = null

    @Column(name = "JOB_ASSESSMENT_CONTRAINTS_ID", precision = 10)
    var job_assessment_contraints_id: Long? = null

    @Column(name = "DATE_CREATED", nullable = false)
    var date_created: java.time.LocalDate? = null

    @Column(name = "CREATED_BY", nullable = false)
    lateinit var created_by: String

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATS_DRAWINGS_ID")
    var statsDrawings: StatsDrawings? = null

}

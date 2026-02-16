package com.vrs.v1.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.LocalDate

@Entity
@Table(name = "Job_Assessment_Contraints")
class JobAssessmentContraints {
    @Id
    @Column(name = "JOB_ASSESSMENT_CONTRAINTS_ID", nullable = false, precision = 10)
    var job_assessment_contraints_id: Long? = null

    @Column(name = "CONSTRAINT", nullable = false)
    lateinit var constraint: String

    @Column(name = "DATE_CREATED", nullable = false)
    var date_created: java.time.LocalDate? = null

    @Column(name = "CREATED_BY", nullable = false)
    lateinit var created_by: String

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JOB_ASSESSMENT_TYPE_ID")
    var jobAssessmentType: JobAssessmentType? = null

}

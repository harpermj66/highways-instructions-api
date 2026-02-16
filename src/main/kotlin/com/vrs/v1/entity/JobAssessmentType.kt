package com.vrs.v1.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.LocalDate

@Entity
@Table(name = "Job_Assessment_Type")
class JobAssessmentType {
    @Id
    @Column(name = "JOB_ASSESSMENT_TYPE_ID", nullable = false, precision = 10)
    var job_assessment_type_id: Long? = null

    @Column(name = "ASSESSMENT_TYPE", nullable = false)
    lateinit var assessment_type: String

    @Column(name = "DATE_CREATED", nullable = false)
    var date_created: java.time.LocalDate? = null

    @Column(name = "CREATED_BY", nullable = false)
    lateinit var created_by: String

    @OneToMany(mappedBy = "jobAssessmentType", cascade = [CascadeType.ALL])
    var jobAssessmentContraintss: MutableList<JobAssessmentContraints> = mutableListOf()

}

package com.vrs.v1.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.LocalDate

@Entity
@Table(name = "Gang_Assignment")
class GangAssignment {
    @Id
    @Column(name = "GANG_ASSIGNMENT_ID", nullable = false, precision = 10)
    var gang_assignment_id: Long? = null

    @Column(name = "GANG")
    var gang: String? = null

    @Column(name = "PROGRAMMED_START_TIME")
    var programmed_start_time: java.time.LocalDate? = null

    @Column(name = "PROGRAMMED_END_TIME")
    var programmed_end_time: java.time.LocalDate? = null

    @Column(name = "READ_RISK_ASSESSMENT_TIME")
    var read_risk_assessment_time: java.time.LocalDate? = null

    @Column(name = "ACTUAL_START_TIME")
    var actual_start_time: java.time.LocalDate? = null

    @Column(name = "RECALCULATION_END_TIME")
    var recalculation_end_time: java.time.LocalDate? = null

    @Column(name = "SUSPEND_START_TIME")
    var suspend_start_time: java.time.LocalDate? = null

    @Column(name = "SUSPEND_END_TIME")
    var suspend_end_time: java.time.LocalDate? = null

    @Column(name = "JOB_END_TIME")
    var job_end_time: java.time.LocalDate? = null

    @Column(name = "MADE_SAFE")
    var made_safe: String? = null

    @Column(name = "COMPLETION_NOTES")
    var completion_notes: String? = null

    @Column(name = "GANG_ID", precision = 10)
    var gang_id: Long? = null

    @Column(name = "GANG_VEHICLE_ID", precision = 10)
    var gang_vehicle_id: Long? = null

    @Column(name = "TIME_SUBMITTED_TO_GANG")
    var time_submitted_to_gang: java.time.LocalDate? = null

    @Column(name = "SUBMITTED_BY")
    var submitted_by: String? = null

    @Column(name = "COMPETENCE")
    var competence: String? = null

    @Column(name = "REASON_FOR_OVERRIDE")
    var reason_for_override: String? = null

    @Column(name = "ORIGINAL_START_TIME")
    var original_start_time: java.time.LocalDate? = null

    @Column(name = "COMPETENCY_OVERRIDE_ID", precision = 10)
    var competency_override_id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JI_ID", insertable = false, updatable = false)
    var jobInstruction: JobInstruction? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JI_ID", insertable = false, updatable = false)
    var incompleteJobInstructions: IncompleteJobInstructions? = null

}

package com.vrs.v1.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.LocalDate

@Entity
@Table(name = "Job_Instruction")
class JobInstruction {
    @Id
    @Column(name = "Job_Instruction_ID", nullable = false, precision = 10)
    var job_instruction_id: Long? = null

    @Column(name = "DATE_CREATED", nullable = false)
    var date_created: java.time.LocalDate? = null

    @Column(name = "CREATED_BY", nullable = false)
    lateinit var created_by: String

    @Column(name = "JOB_INSTRUCTION_NO", precision = 10)
    var job_instruction_no: Long? = null

    @Column(name = "ACTION_OFFICER")
    var action_officer: String? = null

    @Column(name = "STATUS")
    var status: String? = null

    @Column(name = "CONFIRM_JOB", precision = 10)
    var confirm_job: Long? = null

    @Column(name = "CONFIRM_WO", precision = 10)
    var confirm_wo: Long? = null

    @Column(name = "SITEMAN_WO_NO")
    var siteman_wo_no: String? = null

    @Column(name = "CONFIRM_ENQUIRY", nullable = false)
    lateinit var confirm_enquiry: String

    @Column(name = "PRIORITY_CODE")
    var priority_code: String? = null

    @Column(name = "PRIORITY")
    var priority: String? = null

    @Column(name = "DEPOT", nullable = false)
    lateinit var depot: String

    @Column(name = "CURRENT_PRIORITY_CODE")
    var current_priority_code: String? = null

    @Column(name = "CURRENT_PRIORITY")
    var current_priority: String? = null

    @Column(name = "UTILITY_PLANS")
    var utility_plans: String? = null

    @Column(name = "RES_AUTH")
    var res_auth: String? = null

    @Column(name = "LOCATION")
    var location: String? = null

    @Column(name = "FEATURE")
    var feature: String? = null

    @Column(name = "FEATURE_CODE")
    var feature_code: String? = null

    @Column(name = "DEFECT_DESCRIPTION")
    var defect_description: String? = null

    @Column(name = "DEFECT_TYPE")
    var defect_type: String? = null

    @Column(name = "DEFECT_NOTES")
    var defect_notes: String? = null

    @Column(name = "REPAIR_WIDTH", precision = 10)
    var repair_width: Long? = null

    @Column(name = "REPAIR_LENGTH", precision = 10)
    var repair_length: Long? = null

    @Column(name = "REPAIR_DEPTH", precision = 10)
    var repair_depth: Long? = null

    @Column(name = "REPAIR_QUANTITY", precision = 10)
    var repair_quantity: Long? = null

    @Column(name = "SUB_STREET")
    var sub_street: String? = null

    @Column(name = "STREET")
    var street: String? = null

    @Column(name = "SUFFIX")
    var suffix: String? = null

    @Column(name = "LOCALITY")
    var locality: String? = null

    @Column(name = "POST_TOWN")
    var post_town: String? = null

    @Column(name = "POST_CODE")
    var post_code: String? = null

    @Column(name = "EASTING", precision = 6)
    var easting: Int? = null

    @Column(name = "NORTHING", precision = 6)
    var northing: Int? = null

    @Column(name = "LATITUDE", precision = 12, scale = 9)
    var latitude: java.math.BigDecimal? = null

    @Column(name = "LONGITUDE", precision = 12, scale = 9)
    var longitude: java.math.BigDecimal? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JI_ID")
    var incompleteJobInstructions: IncompleteJobInstructions? = null

    @Column(name = "DEPOT_ID", precision = 10)
    var depot_id: Long? = null

    @Column(name = "OFFICERS_ID", precision = 10)
    var officers_id: Long? = null

    @Column(name = "PRIORITY_ID", precision = 10)
    var priority_id: Long? = null

    @Column(name = "RESOLVING_AUTHORITY_ID", precision = 10)
    var resolving_authority_id: Long? = null

    @Column(name = "FEATURES_ID", precision = 10)
    var features_id: Long? = null

    @Column(name = "DEFECTS_ID", precision = 10)
    var defects_id: Long? = null

    @Column(name = "CURRENT_PRIORITY_ID", precision = 10)
    var current_priority_id: Long? = null

    @OneToMany(mappedBy = "jobInstruction", cascade = [CascadeType.ALL])
    var actionLogs: MutableList<ActionLog> = mutableListOf()

    @OneToMany(mappedBy = "jobInstruction", cascade = [CascadeType.ALL])
    var gangAssignments: MutableList<GangAssignment> = mutableListOf()

    @OneToMany(mappedBy = "jobInstruction", cascade = [CascadeType.ALL])
    var jobInstructions: MutableList<JobInstruction> = mutableListOf()

    @OneToMany(mappedBy = "jobInstruction", cascade = [CascadeType.ALL])
    var sorItemss: MutableList<SorItems> = mutableListOf()

    @OneToMany(mappedBy = "jobInstruction", cascade = [CascadeType.ALL])
    var statsDrawingss: MutableList<StatsDrawings> = mutableListOf()

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JI_ID", insertable = false, updatable = false)
    var jobInstruction: JobInstruction? = null

}

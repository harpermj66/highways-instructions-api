package com.vrs.v1.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.LocalDate

@Entity
@Table(name = "Incomplete_Job_Instructions")
class IncompleteJobInstructions {
    @Id
    @Column(name = "JI_ID", nullable = false, precision = 10)
    var ji_id: Long? = null

    @Column(name = "CONFIRM_ENQUIRY", nullable = false)
    lateinit var confirm_enquiry: String

    @Column(name = "Location", nullable = false)
    lateinit var location: String

    @Column(name = "Priority", nullable = false)
    lateinit var priority: String

    @Column(name = "Gang", nullable = false)
    lateinit var gang: String

    @Column(name = "EST_DURATION", nullable = false)
    lateinit var est_duration: String

    @Column(name = "Due_Date", nullable = false)
    var due_date: java.time.LocalDate? = null

    @Column(name = "Status", nullable = false)
    lateinit var status: String

    @OneToMany(mappedBy = "incompleteJobInstructions", cascade = [CascadeType.ALL])
    var actionLogs: MutableList<ActionLog> = mutableListOf()

    @OneToMany(mappedBy = "incompleteJobInstructions", cascade = [CascadeType.ALL])
    var gangAssignments: MutableList<GangAssignment> = mutableListOf()

    @OneToMany(mappedBy = "incompleteJobInstructions", cascade = [CascadeType.ALL])
    var jobInstructions: MutableList<JobInstruction> = mutableListOf()

    @OneToMany(mappedBy = "incompleteJobInstructions", cascade = [CascadeType.ALL])
    var sorItemss: MutableList<SorItems> = mutableListOf()

    @OneToMany(mappedBy = "incompleteJobInstructions", cascade = [CascadeType.ALL])
    var statsDrawingss: MutableList<StatsDrawings> = mutableListOf()

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPOT_ID")
    var depot: Depot? = null

}

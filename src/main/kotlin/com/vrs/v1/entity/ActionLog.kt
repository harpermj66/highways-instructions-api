package com.vrs.v1.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.LocalDate

@Entity
@Table(name = "Action_Log")
class ActionLog {
    @Id
    @Column(name = "ACTION_LOG_ID", nullable = false, precision = 10)
    var action_log_id: Long? = null

    @Column(name = "LOG_TYPE", nullable = false)
    lateinit var log_type: String

    @Column(name = "CREATED_BY", nullable = false)
    lateinit var created_by: String

    @Column(name = "DATE_CREATED", nullable = false)
    var date_created: java.time.LocalDate? = null

    @Column(name = "COMMENTS")
    var comments: String? = null

    @Column(name = "EXTRA_DETAIL")
    var extra_detail: String? = null

    @Column(name = "LOG_TYPE_ID", precision = 10)
    var log_type_id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JI_ID", insertable = false, updatable = false)
    var jobInstruction: JobInstruction? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JI_ID", insertable = false, updatable = false)
    var incompleteJobInstructions: IncompleteJobInstructions? = null

}

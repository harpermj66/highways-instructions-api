package com.vrs.v1.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.LocalDate

@Entity
@Table(name = "Depot")
class Depot {
    @Id
    @Column(name = "DEPOT_ID", nullable = false, precision = 10)
    var depot_id: Long? = null

    @Column(name = "DEPOT", nullable = false)
    lateinit var depot: String

    @Column(name = "DATE_CREATED", nullable = false)
    var date_created: java.time.LocalDate? = null

    @Column(name = "CREATED_BY", nullable = false)
    lateinit var created_by: String

    @OneToMany(mappedBy = "depot", cascade = [CascadeType.ALL])
    var incompleteJobInstructionss: MutableList<IncompleteJobInstructions> = mutableListOf()

}

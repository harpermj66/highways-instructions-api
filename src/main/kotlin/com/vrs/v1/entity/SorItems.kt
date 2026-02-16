package com.vrs.v1.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.LocalDate

@Entity
@Table(name = "SOR_Items")
class SorItems {
    @Id
    @Column(name = "SOR_ITEMS_ID", nullable = false, precision = 10)
    var sor_items_id: Long? = null

    @Column(name = "SOR_DESCRIPTION")
    var sor_description: String? = null

    @Column(name = "SOR_CODE")
    var sor_code: String? = null

    @Column(name = "SOR_QUANTITY", nullable = false, precision = 10)
    var sor_quantity: Long? = null

    @Column(name = "UOM")
    var uom: String? = null

    @Column(name = "RATE", precision = 10)
    var rate: Long? = null

    @Column(name = "DATE_CREATED", nullable = false)
    var date_created: java.time.LocalDate? = null

    @Column(name = "CREATED_BY", nullable = false)
    lateinit var created_by: String

    @Column(name = "SOR_ID", nullable = false, precision = 10)
    var sor_id: Long? = null

    @Column(name = "CODE_PREFIX", nullable = false)
    lateinit var code_prefix: String

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JI_ID", insertable = false, updatable = false)
    var jobInstruction: JobInstruction? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JI_ID", insertable = false, updatable = false)
    var incompleteJobInstructions: IncompleteJobInstructions? = null

}

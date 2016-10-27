package com.shardis.role

import com.shardis.jpa.AuditedEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.SequenceGenerator

/**
 * Created by Tomasz Kucharzyk
 */
@Entity
@SequenceGenerator(allocationSize = 1, name = "sequenceIdGenerator", sequenceName = "sequence_role")
class Role() : AuditedEntity() {

    @Column(nullable = false, length = 250, unique = true, updatable = false)
    var name: String = ""

    @Column(nullable = false)
    var active: Boolean = true
}

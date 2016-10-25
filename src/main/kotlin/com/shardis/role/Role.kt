package com.shardis.role

import com.shardis.jpa.AuditedEntity
import javax.persistence.Entity
import javax.persistence.SequenceGenerator

/**
 * Created by Tomasz Kucharzyk
 */
@Entity
@SequenceGenerator(allocationSize = 1, name = "sequenceIdGenerator", sequenceName = "Sequence_Role")
class Role() : AuditedEntity() {

    var name: String = ""

    var active: Boolean = true
}

package com.shardis.usergroup

import com.shardis.jpa.AuditedEntity
import com.shardis.role.Role
import javax.persistence.*

/**
 * Created by Tomasz Kucharzyk
 */
@Entity
@SequenceGenerator(allocationSize = 1, name = "sequenceIdGenerator", sequenceName = "sequence_user_group")
class UserGroup() : AuditedEntity() {

    @Column(nullable = false, length = 250, unique = true)
    var name: String = ""

    @Column(nullable = false)
    var active: Boolean = true

    @ManyToMany(targetEntity = Role::class, cascade = arrayOf(CascadeType.MERGE, CascadeType.PERSIST))
    var roles: Set<Role> = emptySet()
}

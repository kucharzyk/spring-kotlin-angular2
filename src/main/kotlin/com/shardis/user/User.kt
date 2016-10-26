package com.shardis.user

import com.shardis.jpa.AuditedEntity
import com.shardis.role.Role
import com.shardis.usergroup.UserGroup
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.ManyToMany
import javax.persistence.SequenceGenerator

/**
 * Created by Tomasz Kucharzyk
 */
@Entity
@SequenceGenerator(allocationSize = 1, name = "sequenceIdGenerator", sequenceName = "sequence_user")
class User() : AuditedEntity() {

    constructor(username: String) : this() {
        this.username = username
    }

    var username: String = ""

    @ManyToMany(targetEntity = Role::class, cascade = arrayOf(CascadeType.MERGE, CascadeType.PERSIST))
    var roles: Set<Role> = emptySet()

    @ManyToMany(targetEntity = UserGroup::class, cascade = arrayOf(CascadeType.MERGE, CascadeType.PERSIST))
    var userGroups: Set<UserGroup> = emptySet()

}

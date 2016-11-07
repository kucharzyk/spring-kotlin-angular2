package com.shardis.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.shardis.jpa.AuditedEntity
import com.shardis.role.Role
import com.shardis.usergroup.UserGroup
import javax.persistence.*

/**
 * Created by Tomasz Kucharzyk
 */
@Entity
@SequenceGenerator(allocationSize = 1, name = "sequenceIdGenerator", sequenceName = "sequence_user")
class User() : AuditedEntity() {

    constructor(username: String) : this() {
        this.username = username
    }

    @Column(nullable = false, length = 250, unique = true)
    var username: String = ""

    @Column(nullable = false, length = 120)
    var firstName: String = ""

    @Column(nullable = false, length = 120)
    var lastName: String = ""

    @Column(nullable = false, length = 120)
    var email: String = ""

    @JsonIgnore
    @Column(nullable = false, length = 512)
    var password: String = ""

    @Column(nullable = false)
    var activated: Boolean = false

    @ManyToMany(targetEntity = Role::class, cascade = arrayOf(CascadeType.MERGE))
    var roles: MutableSet<Role> = mutableSetOf()

    @ManyToMany(targetEntity = UserGroup::class, cascade = arrayOf(CascadeType.MERGE))
    var userGroups: MutableSet<UserGroup> = mutableSetOf()

}

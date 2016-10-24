package com.shardis.user

import com.shardis.jpa.AuditedEntity
import javax.persistence.Entity
import javax.persistence.Table

/**
 * Created by Tomasz Kucharzyk
 */
@Entity
@Table(name = "USERS")
class User(var username: String = "", var role: String = "ROLE_USER") : AuditedEntity()

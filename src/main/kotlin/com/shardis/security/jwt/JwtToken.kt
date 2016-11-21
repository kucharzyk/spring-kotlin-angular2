package com.shardis.security.jwt

import com.shardis.jpa.AuditedEntity
import com.shardis.user.User
import java.time.ZonedDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.SequenceGenerator

/**
 * Created by Tomasz Kucharzyk
 */
@Entity
@SequenceGenerator(allocationSize = 1, name = "sequenceIdGenerator", sequenceName = "sequence_jwt_token")
class JwtToken() : AuditedEntity() {

    @Column(nullable = false, length = 39)
    var ipAdress: String? = null

    @Column(nullable = false)
    var expirationDate: ZonedDateTime = ZonedDateTime.now()

    @ManyToOne(optional = false)
    var user: User? = null

    @Column(nullable = false)
    var active = true

    @Column(nullable = false)
    var invalidated = false

    @Column(nullable = true)
    var invalidationDate: ZonedDateTime? = null

}

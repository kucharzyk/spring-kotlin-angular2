package com.shardis.jpa

import org.hibernate.envers.Audited
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass
import javax.persistence.Version
import java.time.LocalDateTime

/**
 * Created by Tomasz Kucharzyk
 */
@Audited
@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
open class AuditedEntity : BaseEntity() {

    @CreatedDate
    var createdDate: LocalDateTime? = null

    @CreatedBy
    var createdBy: Long? = null

    @LastModifiedDate
    var updatedDate: LocalDateTime? = null

    @LastModifiedBy
    var updatedBy: Long? = null

    @Version
    var version: Long? = null
}

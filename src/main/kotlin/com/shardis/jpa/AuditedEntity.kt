package com.shardis.jpa

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.envers.Audited
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass
import javax.persistence.Version

/**
 * Created by Tomasz Kucharzyk
 */
@Audited
@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
open class AuditedEntity : BaseEntity() {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @JsonIgnore
    private var createdDate: LocalDateTime? = null

    @CreatedBy
    @Column(nullable = false, updatable = false)
    @JsonIgnore
    var createdBy: Long? = null

    @LastModifiedDate
    @JsonIgnore
    var updatedDate: LocalDateTime? = null

    @LastModifiedBy
    @JsonIgnore
    var updatedBy: Long? = null

    @Version
    @JsonIgnore
    @Column(nullable = false, updatable = false)
    var version: Long? = null
}

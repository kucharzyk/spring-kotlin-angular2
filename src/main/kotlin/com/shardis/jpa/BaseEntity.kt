package com.shardis.jpa

import java.util.*
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass

/**
 * Created by Tomasz Kucharzyk
 */

@MappedSuperclass
open class BaseEntity() {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "sequenceIdGenerator")
    var id: Long? = null

    val uuid: String = UUID.randomUUID().toString()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        val that = other as BaseEntity?

        return uuid == that!!.uuid
    }

    override fun hashCode(): Int {
        return Objects.hash(uuid)
    }

}

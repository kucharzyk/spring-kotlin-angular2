package com.shardis.role

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * Created by Tomasz Kucharzyk
 */
@Repository
interface RoleRepository : CrudRepository<Role, Long> {
    fun findOneByName(name: String): Role
}

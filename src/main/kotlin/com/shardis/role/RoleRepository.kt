package com.shardis.user

import com.shardis.role.Role
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * Created by Tomasz Kucharzyk
 */
@Repository
interface RoleRepository : CrudRepository<Role, Long> {
    fun findOneByName(name: String): Role
}

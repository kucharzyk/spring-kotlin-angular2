package com.shardis.user

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * Created by Tomasz Kucharzyk
 */
@Repository
interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(username: String): User?
}

package com.shardis.user

import com.shardis.security.jwt.JwtToken
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * Created by Tomasz Kucharzyk
 */
@Repository
interface JwtTokenRepository : CrudRepository<JwtToken, Long> {

}

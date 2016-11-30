package com.shardis.security.jwt

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * Created by Tomasz Kucharzyk
 */
@Repository
interface JwtTokenRepository : CrudRepository<JwtToken, Long>

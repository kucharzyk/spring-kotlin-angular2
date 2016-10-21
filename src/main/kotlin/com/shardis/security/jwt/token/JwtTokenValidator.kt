package com.shardis.security.jwt.token

import com.shardis.ShardisProperties
import com.shardis.security.ShardisUserDetails
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

/**
 * Created by Tomasz Kucharzyk
 */
@Component
open class JwtTokenValidator(private val shardisProperties: ShardisProperties) {

    companion object {
        val log: Logger = LoggerFactory.getLogger(JwtTokenValidator::class.java)
    }

    fun parseToken(token: String?): UserDetails {
        if (token != null) {
            try {
                val body = Jwts.parser()
                    .setSigningKey(shardisProperties.security.secret)
                    .parseClaimsJws(token)
                    .body

                return ShardisUserDetails(body.get("userId", Long::class.java), body.subject, token, listOf(SimpleGrantedAuthority(body.get("role", String::class.java))))
            } catch (e: JwtException) {
                log.error(e.message, e)
            }
        }
        return ShardisUserDetails(-1, "ANONYMOUDE", "ANONYMOUDE", emptyList())
    }
}

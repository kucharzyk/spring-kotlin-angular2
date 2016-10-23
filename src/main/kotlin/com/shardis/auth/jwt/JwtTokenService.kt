package com.shardis.auth.jwt

import com.shardis.ShardisProperties
import com.shardis.auth.ShardisUserDetails
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

/**
 * Created by Tomasz Kucharzyk
 */
@Component
open class JwtTokenService(private val shardisProperties: ShardisProperties) {

    companion object {
        val log: Logger = LoggerFactory.getLogger(JwtTokenService::class.java)
    }

    fun parseToken(token: String?): UserDetails {
        if (token != null) {
            try {
                val body = Jwts.parser()
                    .setSigningKey(shardisProperties.security.secret)
                    .parseClaimsJws(token)
                    .body

                return ShardisUserDetails((body.get("userId") as Int).toLong(), body.subject, token, listOf(SimpleGrantedAuthority(body.get("role") as String)))
            } catch (e: JwtException) {
                log.error(e.message, e)
            }
        }
        return ShardisUserDetails(-1, "ANONYMOUS", "ANONYMOUS", emptyList())
    }

    fun generateToken(username: String, userId: Long): String {
        return Jwts.builder()
            .setSubject(username)
            .claim("userId", userId)
            .claim("role", "ROLE_USER")
            .signWith(SignatureAlgorithm.HS512, shardisProperties.security.secret)
            .compact()
    }
}

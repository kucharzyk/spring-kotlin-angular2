package com.shardis.security.jwt

import com.shardis.ShardisProperties
import com.shardis.extensions.toDate
import com.shardis.security.support.ShardisUserDetails
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

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
                val body:Claims = Jwts.parser()
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
            .setIssuedAt(LocalDateTime.now().toDate())
            .setNotBefore(LocalDateTime.now().toDate())
            .setExpiration(LocalDateTime.now().plusMinutes(30L).toDate())
            .claim("userId", userId)
            .claim("role", "ROLE_USER")
            .signWith(SignatureAlgorithm.HS512, shardisProperties.security.secret)
            .compact()
    }
}

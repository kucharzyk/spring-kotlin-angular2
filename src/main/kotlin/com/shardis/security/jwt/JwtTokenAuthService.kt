package com.shardis.security.jwt

import com.shardis.ShardisProperties
import com.shardis.extensions.toDate
import com.shardis.security.ShardisUserDetailsService
import com.shardis.security.support.ShardisUserDetails
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.InsufficientAuthenticationException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDateTime

/**
 * Created by Tomasz Kucharzyk
 */
@Service
open class JwtTokenAuthService(
    private val shardisProperties: ShardisProperties,
    private val shardisUserDetailsService: ShardisUserDetailsService
) {

    companion object {
        val log: Logger = LoggerFactory.getLogger(JwtTokenAuthService::class.java)
    }

    fun parseToken(token: String?): UserDetails {
        if (token != null) {
            try {
                val body: Claims = Jwts.parser()
                    .setSigningKey(shardisProperties.security.jwtSecret)
                    .parseClaimsJws(token)
                    .body

                return ShardisUserDetails((body.get("userId") as Int).toLong(), body.subject, token, listOf(SimpleGrantedAuthority(body.get("role") as String)))
            } catch (e: JwtException) {
                log.error(e.message, e)
            }
        }
        return ShardisUserDetails(-1, "ANONYMOUS", "ANONYMOUS", emptyList())
    }

    fun generateToken(username: String): String {
        val userDetails: UserDetails = shardisUserDetailsService.loadUserByUsername(username) ?: throw  UsernameNotFoundException("user $username not found")
        if (userDetails is ShardisUserDetails) {
            return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(LocalDateTime.now().toDate())
                .setNotBefore(LocalDateTime.now().toDate())
                .setExpiration(LocalDateTime.now().plusMinutes(30L).toDate())
                .claim("userId", userDetails.id)
                .claim("role", "ROLE_USER")
                .signWith(SignatureAlgorithm.HS512, shardisProperties.security.jwtSecret)
                .compact()
        } else {
            throw InsufficientAuthenticationException("invalid user details")
        }
    }

}

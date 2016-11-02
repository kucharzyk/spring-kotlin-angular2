package com.shardis.security.jwt

import com.shardis.ShardisProperties
import com.shardis.extensions.toDate
import com.shardis.security.support.ShardisUserDetails
import com.shardis.user.UserRepository
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
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
    private val userRepository: UserRepository,
    private val shardisPasswordEncoder: ShardisPasswordEncoder
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

    fun generateToken(username: String, userId: Long): String {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(LocalDateTime.now().toDate())
            .setNotBefore(LocalDateTime.now().toDate())
            .setExpiration(LocalDateTime.now().plusMinutes(30L).toDate())
            .claim("userId", userId)
            .claim("role", "ROLE_USER")
            .signWith(SignatureAlgorithm.HS512, shardisProperties.security.jwtSecret)
            .compact()
    }

    fun authenticate(username: String?, password: String?): String {
        if (username == null) {
            throw UsernameNotFoundException("user not found")
        }

        val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("user not found")

        if (!user.activated) {
            throw DisabledException("user is not activated")
        }

        if (password == null) {
            throw BadCredentialsException("no password provided")
        }
        val encodedPassword = shardisPasswordEncoder.encode(password)

        if (encodedPassword == user.password) {
            return generateToken(username, user.id!!)
        } else {
            throw BadCredentialsException("bad credentials")
        }
    }
}

package com.shardis.security.jwt

import com.shardis.ShardisProperties
import com.shardis.extensions.toDate
import com.shardis.security.support.ShardisUserDetails
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.SignatureException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Service
import java.time.LocalDateTime

/**
 * Created by Tomasz Kucharzyk
 */
@Service
open class JwtTokenAuthService(private val shardisProperties: ShardisProperties) {

    companion object {
        val log: Logger = LoggerFactory.getLogger(JwtTokenAuthService::class.java)
        val AUTHORITIES_FIELD = "authorities"
        val USER_ID_FIELD = "user_id"
    }

    fun createToken(authentication: Authentication, rememberMe: Boolean = false): String {

        val authorities = authentication.authorities
            .map({ authority -> authority.authority })
            .joinToString(separator = ",")

        val now = LocalDateTime.now()
        val validity: LocalDateTime
        if (rememberMe) {
            validity = now.plusSeconds(shardisProperties.security.tokenValidityInSecondsForRememberMe)
        } else {
            validity = now.plusSeconds(shardisProperties.security.tokenValidityInSeconds)
        }

        val userDetails = authentication.details as ShardisUserDetails;

        return Jwts.builder()
            .setSubject(authentication.name)
            .claim(USER_ID_FIELD,userDetails.userId)
            .claim(AUTHORITIES_FIELD, authorities)
            .signWith(SignatureAlgorithm.HS512, shardisProperties.security.jwtSecret)
            .setNotBefore(now.toDate())
            .setExpiration(validity.toDate())
            .compact()
    }

    fun getAuthentication(token: String): Authentication {
        val claims = Jwts.parser()
            .setSigningKey(shardisProperties.security.jwtSecret)
            .parseClaimsJws(token)
            .body

        val authorities = claims.get(AUTHORITIES_FIELD).toString()
            .split(",")
            .map(::SimpleGrantedAuthority)

        val principal = ShardisUserDetails(claims.get(USER_ID_FIELD).toString().toLong(),claims.subject, "", authorities)

        return UsernamePasswordAuthenticationToken(principal, "", authorities)
    }

    fun validateToken(authToken: String): Boolean {
        try {
            Jwts.parser().setSigningKey(shardisProperties.security.jwtSecret).parseClaimsJws(authToken)
            return true
        } catch (e: SignatureException) {
            log.info("Invalid JWT signature: " + e.message)
            return false
        }

    }

}

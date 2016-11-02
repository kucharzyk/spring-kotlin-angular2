package com.shardis.security.jwt

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

/**
 * Created by Tomasz Kucharzyk
 */

open class JwtAuthenticationProvider(private val jwtTokenAuthService: JwtTokenAuthService) : AbstractUserDetailsAuthenticationProvider() {

    override fun supports(authentication: Class<*>): Boolean = JwtAuthenticationToken::class.java.isAssignableFrom(authentication)

    override fun additionalAuthenticationChecks(userDetails: UserDetails, authentication: UsernamePasswordAuthenticationToken) {
    }

    override fun retrieveUser(username: String, authentication: UsernamePasswordAuthenticationToken): UserDetails {
        val jwtAuthenticationToken = authentication as JwtAuthenticationToken
        val token = jwtAuthenticationToken.token
        return jwtTokenAuthService.parseToken(token)
    }
}

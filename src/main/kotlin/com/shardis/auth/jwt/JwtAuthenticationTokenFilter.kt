package com.shardis.auth.jwt

import com.shardis.ShardisProperties
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by Tomasz Kucharzyk
 */
class JwtAuthenticationTokenFilter(val shardisProperties: ShardisProperties) : AbstractAuthenticationProcessingFilter("/**") {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication? {
        val header: String? = request.getHeader(shardisProperties.security.header)
        if (header == null || !header.startsWith("Bearer ")) {
            return AnonymousAuthenticationToken("anonymous", "anonymous", listOf(SimpleGrantedAuthority("ROLE_ANONYMOUS")))
        }
        val authToken = header.substring(7)
        val authRequest = JwtAuthenticationToken(authToken)
        return authenticationManager.authenticate(authRequest)
    }

    override fun successfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain, authResult: Authentication) {
        super.successfulAuthentication(request, response, chain, authResult)
        chain.doFilter(request, response)
    }
}

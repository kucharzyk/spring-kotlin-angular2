package com.shardis.security.jwt

import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by Tomasz Kucharzyk
 */
@Component
open class JwtAuthenticationFailureHandler : AuthenticationFailureHandler {
    override fun onAuthenticationFailure(request: HttpServletRequest, response: HttpServletResponse, exception: AuthenticationException?) {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed");
    }
}

package com.shardis.security.jwt

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by Tomasz Kucharzyk
 */
@Component
open class JwtAuthenticationSuccessHandler() : AuthenticationSuccessHandler {

    @Autowired
    private lateinit var jwtTokenAuthService: JwtTokenAuthService

    override fun onAuthenticationSuccess(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication) {
        val testToken = jwtTokenAuthService.createToken(authentication)
        response.writer.use {
            it.print(testToken)
            it.flush()
        }
        response.status = HttpServletResponse.SC_OK
    }
}

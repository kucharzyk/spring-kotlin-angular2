package com.shardis.auth

import com.shardis.security.jwt.JwtTokenAuthService
import com.shardis.security.support.SecurityUtils
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

/**
 * Created by Tomasz Kucharzyk
 */

@RestController()
@RequestMapping(path = arrayOf("/auth/"))
class AuthController(val jwtTokenAuthService: JwtTokenAuthService) {

    @RequestMapping(path = arrayOf("/token"), method = arrayOf(RequestMethod.GET))
    fun token(request: HttpServletRequest): String {
        return jwtTokenAuthService.createToken(UsernamePasswordAuthenticationToken("test", "", listOf(SimpleGrantedAuthority("ROLE_TEST"))), request)
    }

    @RequestMapping(path = arrayOf("/principal"), method = arrayOf(RequestMethod.GET))
    fun principal(): String {
        return SecurityUtils.getLoggedUserName()
    }

}

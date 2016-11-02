package com.shardis.auth

import com.shardis.auth.support.UsernamePasswordDto
import com.shardis.security.jwt.JwtTokenAuthService
import com.shardis.security.support.SecurityUtils
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * Created by Tomasz Kucharzyk
 */

@RestController()
@RequestMapping(path = arrayOf("/auth/"))
class AuthController(val jwtTokenAuthService: JwtTokenAuthService) {

    @RequestMapping(path = arrayOf("/login"), method = arrayOf(RequestMethod.POST))
    fun login(usernamePasswordDto: UsernamePasswordDto): String {
        val (username,password) = usernamePasswordDto
        return jwtTokenAuthService.authenticate(username,password)
    }

    @RequestMapping(path = arrayOf("/token"), method = arrayOf(RequestMethod.GET))
    fun token(): String {
        return jwtTokenAuthService.generateToken("test", 1)
    }

    @RequestMapping(path = arrayOf("/principal"), method = arrayOf(RequestMethod.GET))
    fun principal(): String {
        return SecurityUtils.getLoggedUserName()
    }

}

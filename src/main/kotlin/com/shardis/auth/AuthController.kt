package com.shardis.auth

import com.shardis.auth.jwt.JwtTokenService
import com.shardis.auth.support.SecurityUtils
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * Created by Tomasz Kucharzyk
 */

@RestController()
@RequestMapping(path = arrayOf("/auth/"))
class AuthController(val jwtTokenService: JwtTokenService) {

    @RequestMapping(path = arrayOf("/token"), method = arrayOf(RequestMethod.GET))
    fun token(): String {
        return jwtTokenService.generateToken("test", 1)
    }

    @RequestMapping(path = arrayOf("/principal"), method = arrayOf(RequestMethod.GET))
    fun principal(): String {
        return SecurityUtils.getLoggedUserName()
    }

}

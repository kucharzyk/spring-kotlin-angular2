package com.shardis.auth

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails

/**
 * Created by Tomasz Kucharzyk
 */
object SecurityUtils {

    fun getLoggedUserName(): String {
        val principal: Any = SecurityContextHolder.getContext().authentication.principal
        if (principal is UserDetails) {
            return principal.username
        } else {
            return principal.toString()
        }
    }
}

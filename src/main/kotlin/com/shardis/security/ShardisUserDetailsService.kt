package com.shardis.security

import com.shardis.security.support.ShardisUserDetails
import com.shardis.user.User
import com.shardis.user.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import javax.transaction.Transactional

/**
 * Created by Tomasz Kucharzyk
 */
@Transactional
@Service
open class ShardisUserDetailsService(val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user: User? = userRepository.findByUsername(username)

        if (user != null) {
            val roles: List<GrantedAuthority> = user.roles.map { SimpleGrantedAuthority(it.name) }
            return ShardisUserDetails(user.id!!, user.username, null, roles)
        }

        return ShardisUserDetails(-1, "ANONYMOUS", null, emptyList())
    }

}

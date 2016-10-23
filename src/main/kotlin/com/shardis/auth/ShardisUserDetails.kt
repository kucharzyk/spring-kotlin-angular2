package com.shardis.auth

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * Created by Tomasz Kucharzyk
 */
class ShardisUserDetails(
    val id: Long,
    private val username: String,
    val token: String,
    private val authorities: Collection<GrantedAuthority>
) : UserDetails {

    override fun getUsername(): String = username

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun getAuthorities(): Collection<GrantedAuthority> = authorities

    override fun isEnabled(): Boolean = true

    override fun getPassword(): String? = null

}

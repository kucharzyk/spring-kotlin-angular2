package com.shardis.security.support

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

/**
 * Created by Tomasz Kucharzyk
 */
class ShardisUserDetails(val userId: Long, username: String, password: String = "", authorities: Collection<GrantedAuthority>) : User(username, password, authorities)

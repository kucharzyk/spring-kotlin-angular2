package com.shardis.security.jwt

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

/**
 * Created by Tomasz Kucharzyk
 */
class JwtAuthenticationToken(val token: String) : UsernamePasswordAuthenticationToken(null, null)

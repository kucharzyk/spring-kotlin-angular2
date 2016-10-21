package com.shardis.security.jwt.token

import io.jsonwebtoken.Jwts

/**
 * Created by Tomasz Kucharzyk
 */
class JwtTokenGenerator {


    fun generateToken(username: String, userId: Long, secret: String): String {
        return Jwts.builder()
            .setSubject(username)
            .claim("userId", userId)
            .claim("role", "ROLE_USER")
            .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, secret)
            .compact()
    }

}

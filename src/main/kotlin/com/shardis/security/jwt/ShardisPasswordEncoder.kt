package com.shardis.security.jwt

import com.shardis.ShardisProperties
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder
import org.springframework.stereotype.Service

/**
 * Created by Tomasz Kucharzyk
 */
@Service
class ShardisPasswordEncoder(shardisProperties: ShardisProperties) : Pbkdf2PasswordEncoder(shardisProperties.security.passwordSecret)

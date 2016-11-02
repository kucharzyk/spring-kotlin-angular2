package com.shardis

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * Created by Tomasz Kucharzyk
 */

@Component
@ConfigurationProperties(prefix = "shardis")
open class ShardisProperties {

    var version = "unknown"
    var security = Security()

    open class Security {
        var header = "Authorization"
        var jwtSecret: String = "secret"
        var passwordSecret: String = "secret"
    }
}

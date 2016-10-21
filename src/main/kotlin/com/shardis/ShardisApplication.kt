package com.shardis

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties

/**
 * Created by Tomasz Kucharzyk
 */

@SpringBootApplication
@EnableConfigurationProperties(ShardisProperties::class)
open class ShardisApplication {

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(ShardisApplication::class.java, *args)
        }
    }

}

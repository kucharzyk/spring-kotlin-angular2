package com.shardis

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * Created by Tomasz Kucharzyk
 */

@SpringBootApplication
open class ShardisApplication {

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(ShardisApplication::class.java, *args)
        }
    }

}

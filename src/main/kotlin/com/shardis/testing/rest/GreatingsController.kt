package com.shardis.testing.rest

import com.shardis.ShardisProperties
import com.shardis.testing.dto.SimpleDto
import com.shardis.testing.services.FibonacciService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * Created by Tomasz Kucharzyk
 */
@RestController()
@RequestMapping(path = arrayOf("/api/"))
class GreatingsController(
    private val fibonacciService: FibonacciService,
    private val shardisProperties: ShardisProperties
) {

    @RequestMapping(path = arrayOf("/test"), method = arrayOf(RequestMethod.GET))
    fun test(): SimpleDto {
        return SimpleDto("Hello Kotlin!", shardisProperties.version)
    }

    @RequestMapping(path = arrayOf("/fib/{x}"), method = arrayOf(RequestMethod.GET))
    fun fibonacci(@PathVariable(value = "x") x: Int) = fibonacciService.fib(x)
}

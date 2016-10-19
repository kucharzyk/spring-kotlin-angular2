package com.shardis.controllers.rest

import com.shardis.dto.SimpleDto
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * Created by Tomasz Kucharzyk
 */
@RestController()
@RequestMapping(path = arrayOf("/api/"))
class GreatingsController {

    @RequestMapping(path = arrayOf("/test"), method = arrayOf(RequestMethod.GET))
    fun test(): SimpleDto {
        return SimpleDto("Hello Kotlin!")
    }
}
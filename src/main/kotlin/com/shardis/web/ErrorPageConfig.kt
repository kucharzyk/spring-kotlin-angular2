package com.shardis.web

import org.springframework.boot.web.servlet.ErrorPage
import org.springframework.boot.web.servlet.ErrorPageRegistrar
import org.springframework.boot.web.servlet.ErrorPageRegistry
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

/**
 * Created by Tomasz Kucharzyk
 */
@Component
open class ErrorPageConfig : ErrorPageRegistrar {
    override fun registerErrorPages(registry: ErrorPageRegistry) {
        registry.addErrorPages(ErrorPage(HttpStatus.NOT_FOUND, "/"))
    }
}

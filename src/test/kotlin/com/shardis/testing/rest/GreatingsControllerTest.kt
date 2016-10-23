package com.shardis.testing.rest

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * Created by Tomasz Kucharzyk
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class GreatingsControllerTest {

    @Autowired private lateinit var greatingsController: GreatingsController

    @Test
    fun controllerIsAutowired() {
        assertNotNull("Greating controller should be autowired", greatingsController)
    }

    @Test
    fun testMethodSetsTextAndDateAndVersion() {
        val testObject = greatingsController.test()
        assertEquals("Hello Kotlin!", testObject.text)
        assertNotEquals("unknown", testObject.version)
        assertTrue(testObject.version.startsWith("0."))
    }

    @Test
    fun fibonnacciWorks() {
        assertEquals("Fibonacci function should works", 233, greatingsController.fibonacci(13))
    }
}

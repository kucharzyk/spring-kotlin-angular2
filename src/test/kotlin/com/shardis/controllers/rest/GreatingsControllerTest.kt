package com.shardis.controllers.rest

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
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

    @Autowired lateinit var greatingsController: GreatingsController

    @Test
    fun controllerIsAutowired() {
        assertNotNull("Greating controller should be autowired", greatingsController)
    }

    @Test
    fun testMethodSetsTextAndDate() {
        val testObject = greatingsController.test()
        assertEquals("Hello Kotlin!", testObject.text)
    }

}
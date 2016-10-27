package com.shardis.testing.services

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * Created by Tomasz Kucharzyk
 */
@RunWith(SpringRunner::class)
@SpringBootTest()
class FibonacciServiceTest {

    @Autowired
    private lateinit var fibonacciService: FibonacciService

    @Test
    fun fibonnacciTest() {
        assertFibbonacci(0, 0)
        assertFibbonacci(1, 1)
        assertFibbonacci(2, 1)
        assertFibbonacci(3, 2)
        assertFibbonacci(4, 3)
        assertFibbonacci(5, 5)
        assertFibbonacci(6, 8)
        assertFibbonacci(7, 13)
        assertFibbonacci(19, 4181)
    }

    @Test(expected = Exception::class)
    fun fibonnacciNegativeTest() {
        assertFibbonacci(-1, 0)
    }

    @Test(expected = Exception::class)
    fun fibonnacciTooLargeTest() {
        assertFibbonacci(65, 0)
    }

    private fun assertFibbonacci(x: Int, expected: Int) {
        assertEquals("fib($x) should be $expected", expected, fibonacciService.fib(x))
    }

}

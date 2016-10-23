package com.shardis.testing.services

import org.springframework.stereotype.Service

/**
 * Created by Tomasz Kucharzyk
 */
@Service
class FibonacciService {

    fun fib(x: Int): Int = when (x) {
        0 -> 0
        1 -> 1
        in 2..64 -> fib(x - 1) + fib(x - 2)
        else -> throw Exception("Number $x is out of range 0..64")
    }

}

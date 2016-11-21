package com.shardis.extensions

import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*

/**
 * Created by Tomasz Kucharzyk
 */

fun ZonedDateTime.toDate(): Date {
    return Date.from(this.toInstant())
}

fun Date.toZonedDateTime(): ZonedDateTime {
    return ZonedDateTime.ofInstant(this.toInstant(), ZoneId.systemDefault())
}

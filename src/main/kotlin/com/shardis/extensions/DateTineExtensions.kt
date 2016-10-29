package com.shardis.extensions

import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

/**
 * Created by Tomasz Kucharzyk
 */

fun LocalDateTime.toDate(): Date {
    return Date.from(this.atZone(ZoneId.systemDefault()).toInstant())
}

fun Date.toLocalDateTime(): LocalDateTime {
    return LocalDateTime.ofInstant(this.toInstant(), ZoneId.systemDefault())
}

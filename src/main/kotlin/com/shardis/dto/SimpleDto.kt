package com.shardis.dto

import java.util.*

/**
 * Created by Tomasz Kucharzyk
 */

data class SimpleDto(val text: String, val version: String, val date: Date = Date())

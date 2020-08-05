package com.valaenthin.vesipresclient

import java.time.LocalDateTime

data class Measurement (
    val name: String,
    var date: LocalDateTime,
    val id: Int,
    val hashG: Long,
    val hashE1: Long,
    val hashE2: Long,
    val hashE3: Long
)
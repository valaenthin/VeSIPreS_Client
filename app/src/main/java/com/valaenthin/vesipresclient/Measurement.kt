package com.valaenthin.vesipresclient

import java.time.LocalDateTime

data class Measurement (
    val name: String,
    var date: LocalDateTime,
    val id: Int,
    val digestG: String,
    val digestE0: String,
    val digestE1: String,
    val digestE2: String
)
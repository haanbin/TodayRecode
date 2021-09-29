package com.haanbin.todayrecode.data.entity

import java.util.*

data class RecodeItem(
    val id: Long,
    val content: String,
    val inputDate: Date,
    val displayDate: String
)
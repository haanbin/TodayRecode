package com.haanbin.todayrecode.ext

import java.text.SimpleDateFormat
import java.util.*

fun Date.toShowString(): String {
    val sdf = SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA)
    return sdf.format(this)
}

fun Date.toCurrentDate(): Date {
    val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.KOREA)
    val currentDateString = sdf.format(this)
    return sdf.parse(currentDateString) ?: Date()
}

fun Date.removeTime(): Date {
    val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.KOREA)
    val currentDateString = sdf.format(this)
    return sdf.parse(currentDateString) ?: Date()
}

fun Calendar.toTomorrowDate(): Date {
    val calendar = this
    calendar.add(Calendar.DATE, 1)
    val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.KOREA)
    val tomorrowDateString = sdf.format(calendar.time)
    return sdf.parse(tomorrowDateString) ?: Date()
}
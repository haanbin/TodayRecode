package com.haanbin.todayrecode.ext

import java.text.SimpleDateFormat
import java.util.*

fun Date.toShowString(): String {
    val sdf = SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA)
    return sdf.format(this)
}
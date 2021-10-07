package com.haanbin.todayrecode.data.entity

import com.haanbin.todayrecode.data.local.entity.Recode
import java.util.*

data class RecodeItem(
    val id: Long,
    val content: String,
    val inputDate: Date,
    val displayDate: String
)

fun RecodeItem.toRecode(): Recode {
    val recode = Recode(
        content,
        inputDate
    )
    recode.id = id
    return recode
}
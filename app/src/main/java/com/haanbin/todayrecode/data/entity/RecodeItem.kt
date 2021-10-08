package com.haanbin.todayrecode.data.entity

import android.os.Parcelable
import com.haanbin.todayrecode.data.local.entity.Recode
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class RecodeItem(
    val id: Long,
    val content: String,
    val inputDate: Date,
    val displayDate: String
) : Parcelable

fun RecodeItem.toRecode(): Recode {
    val recode = Recode(
        content,
        inputDate
    )
    recode.id = id
    return recode
}
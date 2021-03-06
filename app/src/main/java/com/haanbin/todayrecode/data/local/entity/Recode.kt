package com.haanbin.todayrecode.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.haanbin.todayrecode.data.entity.RecodeItem
import com.haanbin.todayrecode.ext.toShowString
import java.util.*

@Entity(tableName = "recode")
data class Recode(
    var content: String,
    val inputDate: Date
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

fun Recode.toRecodeItem(): RecodeItem {
    return RecodeItem(
        id,
        content,
        inputDate,
        displayDate = inputDate.toShowString()
    )
}

fun List<Recode>.toRecodeItems(): List<RecodeItem> {
    return map {
        RecodeItem(
            it.id,
            it.content,
            it.inputDate,
            displayDate = it.inputDate.toShowString()
        )
    }
}
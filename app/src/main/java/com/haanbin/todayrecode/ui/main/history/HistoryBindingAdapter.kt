package com.haanbin.todayrecode.ui.main.history

import android.view.View
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("bind:isCanModify")
fun View.isCanModify(inputDate: Date) {
    val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.KOREA)
    val tomorrowDateString = sdf.format(Date())
    val limitDate = Calendar.getInstance()
    limitDate.time = sdf.parse(tomorrowDateString) ?: Date()
    limitDate.add(Calendar.DATE, -1)

    this.visibility = if (limitDate.time <= inputDate) {
        View.VISIBLE
    } else {
        View.GONE
    }
}
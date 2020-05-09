package com.sherryyuan.buttonup.utils

import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun String.toHumanReadableDateString(): String {
    val date: Date? = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).parse(this)

    val month: CharSequence = DateFormat.format("MMM", date) // Jun
    val day: CharSequence = DateFormat.format("dd", date) // 29
    val year: CharSequence = DateFormat.format("yyyy", date) // 2020

    return "$month $day, $year"
}
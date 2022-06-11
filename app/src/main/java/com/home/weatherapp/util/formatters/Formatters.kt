package com.home.weatherapp.util.formatters

import java.text.SimpleDateFormat
import java.util.*

fun formatEpochTimesToTime(epochTime: Long): String {
    val dt = epochTime * 1000
    val sdf = SimpleDateFormat("h:mm aa", Locale.US)
    return sdf.format(dt)
}

fun formatDateToDay(date: String): String {
    val inFormat = SimpleDateFormat("dd-MM-yyyy")
    val date = inFormat.parse(date)
    val outFormat = SimpleDateFormat("EEEE", Locale.US)
    return outFormat.format(date).replaceFirstChar { it.uppercase() }
}




package com.douglasalipio.luasforecasts.util

import java.text.SimpleDateFormat
import java.util.*


fun String.formatDate(): String {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
    val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH)
    return formatter.format(parser.parse("2018-12-14T09:55:00"))
}

fun isInboundTime(): Boolean {
    val format = SimpleDateFormat("HH", Locale.ENGLISH)
    val hour = format.format(Calendar.getInstance().time).toInt()
    if (hour in 0..12)
        return false
    return true
}

fun getStop(): String {
    return if (isInboundTime()) "STI" else "MAR"
}
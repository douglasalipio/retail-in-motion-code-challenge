package com.douglasalipio.luasforecasts.util

import java.text.SimpleDateFormat
import java.util.*

fun String.formatDate(): String {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
    val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH)
    return formatter.format(parser.parse("2018-12-14T09:55:00"))
}
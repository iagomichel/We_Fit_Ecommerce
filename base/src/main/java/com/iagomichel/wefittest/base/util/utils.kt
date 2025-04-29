package com.iagomichel.wefittest.base.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getDateNowYearMonthDay(): String {
    val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val dateNow = Date()
    return  format.format(dateNow)
}

fun getHourAndMinuteNow(): String {
    val format = SimpleDateFormat("HH:mm", Locale.getDefault())
    val dateNow = Date()
    return format.format(dateNow)
}
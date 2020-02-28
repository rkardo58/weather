package com.example.weather.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

fun String.convertDoubleStringToInt() : Int{
    return this.toDouble().toInt()
}

fun String.getDate() : Calendar{
    val calendar = Calendar.getInstance()
    calendar.time = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(this)
    return calendar
}

open class Utils {
    companion object{

        fun dateToString(date: String) : String{
            val l = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date)
            val sdf = SimpleDateFormat("EEEE', 'MMMM', 'dd")
            return sdf.format(l)
        }
    }
}
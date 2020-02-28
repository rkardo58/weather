package com.example.weather.utils

import java.text.SimpleDateFormat

class Utils {
    companion object{

        fun dateToString(date: String) : String{
            val l = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date)
            val sdf = SimpleDateFormat("EEEE', 'MMMM', 'dd")
            return sdf.format(l)
        }

        fun convertDoubleStringToInt(string: String) : Int{
            return string.toDouble().toInt()
        }
    }
}
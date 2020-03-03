package com.example.weather.utils

import android.annotation.SuppressLint
import com.example.weather.model.TemperatureModel
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

fun String.convertDoubleStringToInt() : Int{
    return try {
        this.toDouble().toInt()
    } catch (e : Exception){
        0
    }
}

@SuppressLint("SimpleDateFormat")
fun String.getDate() : Calendar{
    return try {
        val calendar = Calendar.getInstance()
        calendar.time = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(this)!!
        calendar
    } catch (e: Exception){
        Calendar.getInstance()
    }
}

@SuppressLint("SimpleDateFormat")
fun String.dateToCompostDate() : String{
    return try {
        val l = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(this)!!
        val sdf = SimpleDateFormat("EEEE', 'MMMM', 'dd")
        sdf.format(l)
    } catch (e : Exception){
        ""
    }

}

open class Utils {
    companion object{

        fun getListsByDay(temperatureList: List<TemperatureModel>): ArrayList<List<TemperatureModel>> {
            val list = ArrayList<List<TemperatureModel>>()
            var position = 0
            for (temp: TemperatureModel in temperatureList) {
                val index = temperatureList.indexOf(temp)
                if (index == temperatureList.lastIndex ||  (temp.date.getDate().get(Calendar.DAY_OF_YEAR)
                            != temperatureList[index + 1].date.getDate().get(Calendar.DAY_OF_YEAR)
                            || temperatureList.last() == temp)) {

                    val newList = temperatureList.subList(position, index + 1)
                    list.add(newList)
                    position = index + 1
                }
            }
            return list
        }
    }
}
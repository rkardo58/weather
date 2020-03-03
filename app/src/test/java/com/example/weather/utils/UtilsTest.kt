package com.example.weather.utils

import com.example.weather.model.DegreesModel
import com.example.weather.model.TemperatureModel
import com.example.weather.model.WeatherModel
import com.example.weather.utils.Utils.Companion.getListsByDay
import org.junit.Assert.*
import org.junit.Test

class UtilsTest{

    @Test
    fun getSeparatedListsByDay_emptyList_returnEmptyList(){
        val listOfTemp = emptyList<TemperatureModel>()
        assert(getListsByDay(listOfTemp) == emptyList<List<List<TemperatureModel>>>())
    }

    @Test
    fun getSeparatedListsByDay_onlyOneDay_returnListSize1(){
        val temperatureModel = TemperatureModel(1,"200", DegreesModel("12.3"), listOf(WeatherModel(800,"Cloud", "cloud")), "2020-01-07 15:00:00")
        val listOfTemp = listOf(temperatureModel)
        val result = getListsByDay(listOfTemp)
        assert(result.size == 1)
        assert(result[0].size == 1)
    }

    @Test
    fun getSeparatedListsByDay_multipleTempsOnOnlyOneDay_returnListSize1(){
        val temperatureModel = TemperatureModel(1,"200", DegreesModel("12.3"), listOf(WeatherModel(800,"Cloud", "cloud")), "2020-01-07 15:00:00")
        val temperatureModel1 = TemperatureModel(1,"200", DegreesModel("12.3"), listOf(WeatherModel(800,"Cloud", "cloud")), "2020-01-07 15:00:00")
        val temperatureModel2 = TemperatureModel(1,"200", DegreesModel("12.3"), listOf(WeatherModel(800,"Cloud", "cloud")), "2020-01-07 15:00:00")
        val temperatureModel3 = TemperatureModel(1,"200", DegreesModel("12.3"), listOf(WeatherModel(800,"Cloud", "cloud")), "2020-01-07 15:00:00")
        val temperatureModel4 = TemperatureModel(1,"200", DegreesModel("12.3"), listOf(WeatherModel(800,"Cloud", "cloud")), "2020-01-07 15:00:00")
        val temperatureModel5 = TemperatureModel(1,"200", DegreesModel("12.3"), listOf(WeatherModel(800,"Cloud", "cloud")), "2020-01-07 15:00:00")

        val listOfTemp = listOf(temperatureModel, temperatureModel1, temperatureModel2, temperatureModel3, temperatureModel4, temperatureModel5)

        val result = getListsByDay(listOfTemp)

        assert(result.size == 1)
        assert(result[0].size == 6)
    }

    @Test
    fun getSeparatedListsByDay_multipleDays_returnListSize1(){
        val temperatureModel = TemperatureModel(1,"200", DegreesModel("12.3"), listOf(WeatherModel(800,"Cloud", "cloud")), "2020-01-07 15:00:00")
        val temperatureModel1 = TemperatureModel(1,"200", DegreesModel("12.3"), listOf(WeatherModel(800,"Cloud", "cloud")), "2020-01-08 15:00:00")
        val temperatureModel2 = TemperatureModel(1,"200", DegreesModel("12.3"), listOf(WeatherModel(800,"Cloud", "cloud")), "2020-01-08 15:00:00")
        val temperatureModel3 = TemperatureModel(1,"200", DegreesModel("12.3"), listOf(WeatherModel(800,"Cloud", "cloud")), "2020-01-09 15:00:00")
        val temperatureModel4 = TemperatureModel(1,"200", DegreesModel("12.3"), listOf(WeatherModel(800,"Cloud", "cloud")), "2020-01-10 15:00:00")
        val temperatureModel5 = TemperatureModel(1,"200", DegreesModel("12.3"), listOf(WeatherModel(800,"Cloud", "cloud")), "2020-01-10 15:00:00")
        val temperatureModel6 = TemperatureModel(1,"200", DegreesModel("12.3"), listOf(WeatherModel(800,"Cloud", "cloud")), "2020-01-11 15:00:00")

        val listOfTemp = listOf(temperatureModel, temperatureModel1, temperatureModel2, temperatureModel3, temperatureModel4, temperatureModel5, temperatureModel6)

        val result = getListsByDay(listOfTemp)

        assert(result.size == 5)
        assert(result[0].size == 1)
        assert(result[1].size == 2)
        assert(result[2].size == 1)
        assert(result[3].size == 2)
        assert(result[4].size == 1)
    }
}
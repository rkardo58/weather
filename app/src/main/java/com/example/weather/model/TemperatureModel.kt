package com.example.weather.model

import com.google.gson.annotations.SerializedName

class TemperatureModel {
    var dt = 0
    @SerializedName("cod")
    lateinit var code : String
    lateinit var main : DegreesModel
    lateinit var weather : List<WeatherModel>
    var dt_txt = ""
}
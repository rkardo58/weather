package com.example.weather.model

import com.google.gson.annotations.SerializedName

class OpenWeatherResponseModel {

    @SerializedName("cod")
    lateinit var code : String
    var message = 0
    lateinit var cnt : String
    lateinit var list : List<TemperatureModel>
}
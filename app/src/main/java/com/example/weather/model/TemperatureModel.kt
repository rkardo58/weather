package com.example.weather.model

import com.google.gson.annotations.SerializedName

class TemperatureModel {

    constructor(dt: Int, code: String, main: DegreesModel, weather: List<WeatherModel>, date: String) {
        this.dt = dt
        this.code = code
        this.main = main
        this.weather = weather
        this.date = date
    }

    var dt = 0
    @SerializedName("cod")
    lateinit var code : String
    lateinit var main : DegreesModel
    lateinit var weather : List<WeatherModel>
    @SerializedName("dt_txt")
    var date = ""
}
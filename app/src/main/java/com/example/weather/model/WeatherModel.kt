package com.example.weather.model

class WeatherModel {
    constructor(id : Int, main : String, description: String){
        this.id = id
        this.main = main
        this.description = description
    }
    var id : Int = 0
    var main = ""
    var description = ""
    var icon = ""
}
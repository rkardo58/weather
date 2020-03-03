package com.example.weather.utils

import com.github.mikephil.charting.formatter.ValueFormatter


class MyYAxisValueFormatter : ValueFormatter() {

    override fun getFormattedValue(value: Float): String {
        return "$value ºC"
    }
}
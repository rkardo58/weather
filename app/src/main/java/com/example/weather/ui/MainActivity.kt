package com.example.weather.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weather.R
import com.example.weather.databinding.ActivityMainBinding
import com.example.weather.model.TemperatureModel
import com.example.weather.utils.getDate
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.lifecycleOwner = this
        binding.weatherViewModel = viewModel

        viewModel.daysWeatherList.observe(this, Observer {
            val adapter = DaysAdapter(this, it)
            binding.adpater.adapter = adapter
        })

        viewModel.currentWeather.observe(this, Observer {
            setImage(it.weather[0].id)
        })
    }

    private fun setImage(id: Int) {
        binding.topImage.setImageDrawable( ContextCompat.getDrawable(this, when{
            id > 800 -> R.drawable.cloud
            id == 800 -> R.drawable.sun
            id >= 700 -> R.drawable.mist
            id >= 600 -> R.drawable.snow
            id >= 300 -> R.drawable.rain
            id >= 200 -> R.drawable.thunder
            else -> R.drawable.sun
        }))
    }
}

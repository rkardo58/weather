package com.example.weather.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weather.R
import com.example.weather.databinding.ActivityMainBinding
import com.example.weather.model.TemperatureModel

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.lifecycleOwner = this
        binding.weatherViewModel = viewModel

        viewModel.daysWeatherList.observe(this, Observer {
            val list = ArrayList<List<TemperatureModel>>()
            val newList = ArrayList<TemperatureModel>()
            val newList2 = ArrayList<TemperatureModel>()
            val newList3 = ArrayList<TemperatureModel>()
            for (temp : TemperatureModel in it){
                when {
                    it.indexOf(temp)< it.size/3 -> newList.add(temp)
                    it.indexOf(temp)< (it.size/3)*2 -> newList2.add(temp)
                    else -> newList3.add(temp)
                }

            }
            list.add(newList)
            list.add(newList2)
            list.add(newList3)
            val adapter = DaysAdapter(this, list)
            binding.adpater.adapter = adapter
        })

        viewModel.currentWeather.observe(this, Observer {
            Log.d("artur", it.main.temp.toString())
        })

    }
}

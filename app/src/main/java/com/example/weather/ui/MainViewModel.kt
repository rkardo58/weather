package com.example.weather.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.communication.OpenWeatherApi
import com.example.weather.model.TemperatureModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel: ViewModel() {

    enum class StatusEnum{
        SUCCESS, FAILURE, LOADING
    }
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )
    private var _daysWeatherList = MutableLiveData<List<TemperatureModel>>()
    private var _currentWeather = MutableLiveData<TemperatureModel>()
    val daysWeatherList : LiveData<List<TemperatureModel>> get() = _daysWeatherList
    val currentWeather : LiveData<TemperatureModel> get() = _currentWeather
    private var _status = MutableLiveData<StatusEnum>()
    val status: LiveData<StatusEnum> get() = _status
    var temp = "aaaaaaaa"

    init {
        _status.value = StatusEnum.LOADING
        get5DayWeather()
        getCurrentWeather()
    }


    private fun getCurrentWeather(){
        coroutineScope.launch {

            try {
                val getCurrentDeferred = OpenWeatherApi.retrofitService.getCurrentWeatherAsync("porto")
                val currentTemp = getCurrentDeferred.await()
                when (currentTemp.code){
                    "200" -> {
                        _currentWeather.value = currentTemp
                        _status.value =
                            StatusEnum.SUCCESS
                    } else -> _status.value =
                    StatusEnum.FAILURE
                }

            } catch (e : Exception){
                Log.d("aaa","aaaa")
            }
        }
    }

    private fun get5DayWeather(){
        coroutineScope.launch {

            try {
                val getCurrentDeferred = OpenWeatherApi.retrofitService.getFutureWeatherAsync("porto")
                val currentTemp = getCurrentDeferred.await()
                when (currentTemp.code){
                    "200" -> {
                        _daysWeatherList.value = currentTemp.list
                        _status.value =
                            StatusEnum.SUCCESS
                    } else -> _status.value =
                    StatusEnum.FAILURE
                }

            } catch (e : Exception){
                Log.d("aaa","aaaa")
            }
        }
    }

}
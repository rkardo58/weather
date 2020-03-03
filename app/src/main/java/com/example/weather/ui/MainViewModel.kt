package com.example.weather.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weather.R
import com.example.weather.communication.OpenWeatherApi
import com.example.weather.model.TemperatureModel
import com.example.weather.utils.Utils
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import com.example.weather.utils.convertDoubleStringToInt
import kotlin.collections.ArrayList

const val SUCCESS_REQUEST = "200"

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    enum class StatusEnum{
        SUCCESS, FAILURE, LOADING
    }
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )
    private var _daysWeatherList = MutableLiveData<List<List<TemperatureModel>>>()
    private var _currentWeather = MutableLiveData<TemperatureModel>()
    val daysWeatherList : LiveData<List<List<TemperatureModel>>> get() = _daysWeatherList
    val currentWeather : LiveData<TemperatureModel> get() = _currentWeather
    private var _statusCurrent = MutableLiveData<StatusEnum>()
    val statusCurrent: LiveData<StatusEnum> get() = _statusCurrent
    private var _statusDays = MutableLiveData<StatusEnum>()
    val statusDays: LiveData<StatusEnum> get() = _statusDays
    private var _dataSet = MutableLiveData<LineDataSet>()
    val dataSet : LiveData<LineDataSet> get() = _dataSet
    var city = "Porto"

    init {
        _statusCurrent.value = StatusEnum.LOADING
        _statusDays.value = StatusEnum.LOADING
        getData()
    }

    fun getData() {
        get5DayWeather()
        getCurrentWeather()
    }

    private fun getCurrentWeather(){
        coroutineScope.launch {

            try {
                val getCurrentDeferred = OpenWeatherApi.retrofitService.getCurrentWeatherAsync(city)
                val currentTemp = getCurrentDeferred.await()
                when (currentTemp.code){
                    SUCCESS_REQUEST -> {
                        currentTemp.main.temp = currentTemp.main.temp.convertDoubleStringToInt().toString() + "º"
                        _currentWeather.value = currentTemp
                        _statusCurrent.value = StatusEnum.SUCCESS
                    } else -> _statusCurrent.value = StatusEnum.FAILURE
                }

            } catch (e : Exception){
                _statusCurrent.value = StatusEnum.FAILURE
            }
        }
    }

    private fun get5DayWeather(){
        coroutineScope.launch {

            try {
                val getCurrentDeferred = OpenWeatherApi.retrofitService.getFutureWeatherAsync(city)
                val currentTemp = getCurrentDeferred.await()
                when (currentTemp.code){
                    SUCCESS_REQUEST -> {
                        _daysWeatherList.value = Utils.getListsByDay(currentTemp.list)
                        if (currentTemp.list.isNotEmpty())
                            setUpGraph(currentTemp.list)
                        _statusDays.value = StatusEnum.SUCCESS
                    } else -> _statusDays.value = StatusEnum.FAILURE
                }

            } catch (e : Exception){
                _statusDays.value = StatusEnum.FAILURE
            }
        }
    }

    private fun setUpGraph(list: List<TemperatureModel>) {

        val entries = ArrayList<Entry>()
        for (temp : TemperatureModel in list){
            entries.add(Entry(list.indexOf(temp).toFloat(), temp.main.temp.convertDoubleStringToInt().toFloat()))
        }

        _dataSet.value = LineDataSet(entries, context.getString(R.string.temperature))
    }

}
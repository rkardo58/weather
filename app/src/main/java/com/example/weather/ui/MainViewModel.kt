package com.example.weather.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weather.R
import com.example.weather.communication.OpenWeatherApi
import com.example.weather.model.TemperatureModel
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import com.example.weather.utils.convertDoubleStringToInt
import com.example.weather.utils.getDate
import java.util.*
import kotlin.collections.ArrayList

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
    private var _status = MutableLiveData<StatusEnum>()
    val status: LiveData<StatusEnum> get() = _status
    private var _dataSet = MutableLiveData<LineDataSet>()
    val dataSet : LiveData<LineDataSet> get() = _dataSet

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
                        _daysWeatherList.value = getListsByDay(currentTemp.list)
                        if (currentTemp.list.isNotEmpty())
                            setUpGraph(currentTemp.list)
                        _status.value = StatusEnum.SUCCESS
                    } else -> _status.value =
                    StatusEnum.FAILURE
                }

            } catch (e : Exception){
                Log.d("aaa","aaaa")
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

    private fun getListsByDay(temperatureList: List<TemperatureModel>): ArrayList<List<TemperatureModel>> {
        val list = ArrayList<List<TemperatureModel>>()
        var position = 0
        for (temp: TemperatureModel in temperatureList) {
            if (temperatureList.indexOf(temp) != 0 && (temp.dt_txt.getDate().get(Calendar.DAY_OF_YEAR) != temperatureList[(temperatureList.indexOf(
                    temp
                ) - 1)].dt_txt.getDate().get(Calendar.DAY_OF_YEAR) || temperatureList.last() == temp)
            ) {
                val newList = temperatureList.subList(position, temperatureList.indexOf(temp))
                list.add(newList)
                position = temperatureList.indexOf(temp)
            }
        }
        return list
    }


}
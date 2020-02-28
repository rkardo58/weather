package com.example.weather.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.weather.communication.OpenWeatherApi
import com.example.weather.model.DegreesModel
import com.example.weather.model.OpenWeatherResponseModel
import com.example.weather.model.TemperatureModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository() {

    /*val games : LiveData<List<DegreesModel>> = database.gameDatabaseDao.getAllGames()

    suspend fun refreshGames(user: String, scope: CoroutineScope){
        withContext(scope.coroutineContext){
            OpenWeatherApi.retrofitService.getCurrentWeather("porto").enqueue(object :
                Callback<TemperatureModel> {
                override fun onFailure(call: Call<TemperatureModel>, t: Throwable) {
                    Log.d("aaa", "aaa")
                }

                override fun onResponse(call: Call<TemperatureModel>, response: Response<TemperatureModel>) {
                    Log.d("aaa", "aaa")
                }

            })
        }
    }

    override fun onSuccess(gamePair: Pair<Int, List<BggGame>>) {
        Requests.getGames(gamePair.second)
        database.gameDatabaseDao.insertAll(gamePair.second)
    }

    override fun onFail(error: String) {
        //CoroutineScope(Main).launch { Toast.makeText(conte, error, Toast.LENGTH_LONG).show() }
    }*/
}
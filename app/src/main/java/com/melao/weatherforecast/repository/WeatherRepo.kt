package com.melao.weatherforecast.repository

import android.util.Log
import com.melao.weatherforecast.data.DataOrException
import com.melao.weatherforecast.model.Weather
import com.melao.weatherforecast.model.WeatherObject
import com.melao.weatherforecast.network.WeatherAPI
import javax.inject.Inject

class WeatherRepo @Inject constructor(private val api: WeatherAPI) {
    suspend fun getWeather(cityQuery: String):
            DataOrException<Weather, Boolean, Exception> {
        val response = try {
            api.getWeather(query = cityQuery)
        } catch (e: Exception) {
            Log.d("INSIDE", "getWeather: $e")
            return DataOrException(e = e)
        }
        Log.d("OUTSIDE", "getWeather: $response")
        return DataOrException(data = response)
    }
}
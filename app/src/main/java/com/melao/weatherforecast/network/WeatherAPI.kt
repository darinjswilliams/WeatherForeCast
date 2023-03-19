package com.melao.weatherforecast.network

import com.melao.weatherforecast.model.Weather
import com.melao.weatherforecast.model.WeatherObject
import com.melao.weatherforecast.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherAPI {
    @GET(value = Constants.PATH_FORCAST)
    suspend fun getWeather(
        @Query(Constants.QUERY_PARAM_Q) query: String,
        @Query(Constants.QUERY_WEATHER_UNITS) units: String = "imperial",
        @Query(Constants.QUERY_PARAM_APPID) appid: String = Constants.API_KEY
    ): Weather
}
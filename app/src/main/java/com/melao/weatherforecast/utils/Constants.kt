package com.melao.weatherforecast.utils

import com.melao.weatherforecast.BuildConfig

object Constants {
    const val BASE_URL = "https://api.openweathermap.org/"
    const val API_KEY = BuildConfig.WEATHER_KEY
    const val PATH_FORCAST = "data/2.5/forecast/daily"
    const val QUERY_PARAM_Q = "q"
    const val QUERY_WEATHER_UNITS = "units"
    const val QUERY_PARAM_APPID = "appid"
}
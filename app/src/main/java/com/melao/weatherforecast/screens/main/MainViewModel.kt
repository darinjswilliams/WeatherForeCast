package com.melao.weatherforecast.screens.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melao.weatherforecast.data.DataOrException
import com.melao.weatherforecast.model.Weather
import com.melao.weatherforecast.model.WeatherObject
import com.melao.weatherforecast.repository.WeatherRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepo) : ViewModel() {
  suspend fun getWeatherData(city: String):
          DataOrException<Weather, Boolean, Exception>{
      return repository.getWeather(cityQuery = city)
  }


}
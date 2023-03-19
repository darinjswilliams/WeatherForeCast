package com.melao.weatherforecast.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.melao.weatherforecast.data.DataOrException
import com.melao.weatherforecast.model.City
import com.melao.weatherforecast.model.Weather
import com.melao.weatherforecast.model.WeatherItem
import com.melao.weatherforecast.navigation.WeatherScreens
import com.melao.weatherforecast.screens.main.MainViewModel
import com.melao.weatherforecast.utils.formatDate
import com.melao.weatherforecast.utils.formatDecimals
import com.melao.weatherforecast.widgets.*

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    city: String?
) {
    Log.d("Main Screen: City", "MainScreen: $city")

    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = mainViewModel.getWeatherData(city = city.toString())
    }.value  // get the value to finalize weather date

    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else if (weatherData.data != null) {
        MainScaffold(weather = weatherData.data!!, navController)

    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScaffold(weather: Weather, navController: NavController) {
    Scaffold(topBar = {
        WeatherAppBar(
            title = weather.city.name + ", ${weather.city.country}",
            navController = navController,
            onAddActionClicked = {
                navController.navigate(WeatherScreens.SearchScreen.name)
            },
            elevation = 5.dp
        ) {
            Log.d("TAG", "MainScaffold:  ButtonClicked")
        }
    }) {
        MainContent(data = weather)
    }
}

@Composable
fun MainContent(data: Weather) {
    val weatherItem = data.list[0]
    val imageUrl = "https://openweathermap.org/img/wn/${weatherItem.weather[0].icon}.png"
    Column(
        Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            //Format the date
            text = formatDate(weatherItem.dt),
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSecondary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(6.dp)
        )

        Surface(
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //Image
                WeatherStateImage(imageUrl = imageUrl)

                //Format the day with degree option + shift + 8 will create degree symbol
                Text(
                    text = formatDecimals(weatherItem.temp.day) + "°",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(text = weatherItem.weather[0].main, fontStyle = FontStyle.Italic)
            }
        }
        HumidityWindPressureRow(weather = data.list[0])
        //Add divider
        Divider()

        //SunSetSunRise
        SunSetSunRiseRow(weather = data.list[0])

        Divider()


        Text(
            text = "This Week",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.ExtraBold
        )

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            color = Color(0XFFEEF1EF),
            shape = RoundedCornerShape(size = 14.dp)
        ) {
            //Create Lazy Column juet recylerview
            LazyColumn(modifier = Modifier.padding(2.dp), contentPadding = PaddingValues(1.dp)) {
                items(items = data.list) { item: WeatherItem ->
                    WeatherDetailRow(weather = item)

                }
            }
        }

    }

}




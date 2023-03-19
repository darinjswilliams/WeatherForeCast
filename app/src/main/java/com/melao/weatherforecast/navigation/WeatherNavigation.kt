package com.melao.weatherforecast.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.melao.weatherforecast.screens.WeatherSplashScreen
import com.melao.weatherforecast.screens.MainScreen
import com.melao.weatherforecast.screens.main.MainViewModel
import com.melao.weatherforecast.screens.search.SearchScreen

@Composable
@ExperimentalComposeUiApi
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = WeatherScreens.SplashScreen.name
    ) {
        //Construct Navigation
        composable(WeatherScreens.SplashScreen.name) {
            //Create the Actual Screen
            WeatherSplashScreen(navController = navController)
        }

        //passing the city name along www.google.com/cityname="seattle"

        val route = WeatherScreens.MainScreen.name

        composable("$route/{city}",
            arguments = listOf(
                navArgument(name = "city") {
                    type = NavType.StringType
                }
            )
        ) { navBack ->
            navBack.arguments?.getString("city").let {city ->

                val mainViewModel = hiltViewModel<MainViewModel>()
                //Create the Actual Screen
                MainScreen(navController = navController, mainViewModel, city = city)
            }
        }

        composable(WeatherScreens.SearchScreen.name) {

            //Create the Actual Screen
            SearchScreen(navController = navController)
        }
    }

}
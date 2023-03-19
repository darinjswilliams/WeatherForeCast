package com.melao.weatherforecast.navigation

enum class WeatherScreens {
    SplashScreen,
    MainScreen,
    AboutScreen,
    FavoriteScreen,
    SearchScreen,
    SettingsScreen;

    companion object {
        fun fromRoute(route: String?): WeatherScreens
                = when(route?.substringBefore("/")){
            SplashScreen.name -> SplashScreen
            MainScreen.name -> MainScreen
            null -> MainScreen
            else -> throw IllegalArgumentException("Route: $route is not recognized")
        }
    }
}
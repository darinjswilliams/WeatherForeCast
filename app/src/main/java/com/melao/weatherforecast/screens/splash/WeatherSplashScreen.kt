package com.melao.weatherforecast.screens

import android.util.Log
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.melao.weatherforecast.R
import com.melao.weatherforecast.navigation.WeatherScreens
import kotlinx.coroutines.delay

@Composable

fun WeatherSplashScreen(navController: NavController) {

    val defaultCity = "Dallas"

    //use to controll the scale of our animation
    val scale = remember {
        Animatable(0f)
    }

    //it runs suspend functions in the scope of a composable
    LaunchedEffect(key1 = true , block = {
        scale.animateTo(targetValue = 0.9f, animationSpec = tween(
            durationMillis = 800,
            easing = {
                OvershootInterpolator(8f)
                    .getInterpolation(it)
            }
        ) )
        delay(2000L)
        Log.d("TAG", "WeatherSplashScreen: DefaultCity $defaultCity")
        navController.navigate(WeatherScreens.MainScreen.name +"/$defaultCity" )
    } )

    Surface(
        modifier = Modifier
            .padding(15.dp)
            .size(330.dp),
        shape = CircleShape,
        color = Color.White,
        border = BorderStroke(width = 2.dp, color = Color.LightGray)
    ) {
        Column(
            modifier = Modifier.padding(1.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.sun),
                contentDescription = stringResource(id = R.string.sun),
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(dimensionResource(id = R.dimen.iconsize))
            )
            Text(text = "Find the Sun?",
            style = MaterialTheme.typography.h5,
            color = Color.LightGray)
        }
    }
}
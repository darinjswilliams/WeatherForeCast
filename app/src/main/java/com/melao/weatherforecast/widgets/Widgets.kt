package com.melao.weatherforecast.widgets

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.melao.weatherforecast.R
import com.melao.weatherforecast.model.WeatherItem
import com.melao.weatherforecast.utils.formatDate
import com.melao.weatherforecast.utils.formatDateTime
import com.melao.weatherforecast.utils.formatDecimals


@Composable
fun WeatherDetailRow(weather: WeatherItem) {
    val imageUrl = "https://openweathermap.org/img/wn/${weather.weather[0].icon}.png"

    //Create Surface
    Surface(
        Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                //Split on the comma and grab the day
                text = formatDate(weather.dt).split(",")[0],
                modifier = Modifier.padding(start = 5.dp)
            )

            WeatherStateImage(imageUrl = imageUrl)

            Surface(
                modifier = Modifier.padding(0.dp),
                shape = CircleShape,
                color = Color(0xFFFFC400)
            ) {
                Text(
                    text = weather.weather[0].description,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.caption
                )

            }

            //Using annotated Text
            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Blue.copy(alpha = 0.7f), fontWeight = FontWeight.SemiBold
                    )
                ){
                    append(formatDecimals(weather.temp.max) + "°")
                }
                withStyle(style = SpanStyle(color = Color.LightGray)){
                    append(formatDecimals(weather.temp.min) + "°")
                }
            })
        }
    }

}


@Composable
fun SunSetSunRiseRow(weather: WeatherItem) {
    Row(
        modifier = Modifier
            .padding(top = 15.dp, bottom = 6.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Icon(
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "sunrise icon",
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = formatDateTime(weather.sunrise),
                style = MaterialTheme.typography.caption
            )
        }



        Row {
            Text(
                text = formatDateTime(weather.sunset),
                style = MaterialTheme.typography.caption
            )
            Icon(
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = "sunset icon",
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Composable
fun HumidityWindPressureRow(weather: WeatherItem) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.humidity),
                contentDescription = "humidity icon",
                modifier = Modifier.size(20.dp)
            )
            Text(text = "${weather.humidity}%", style = MaterialTheme.typography.caption)
        }
        Row() {
            Icon(
                painter = painterResource(id = R.drawable.pressure),
                contentDescription = "pressure icon",
                modifier = Modifier.size(20.dp)
            )
            Text(text = "${weather.pressure} psi", style = MaterialTheme.typography.caption)
        }
        Row() {
            Icon(
                painter = painterResource(id = R.drawable.wind),
                contentDescription = "wind icon",
                modifier = Modifier.size(20.dp)
            )
            Text(text = "${weather.speed} mph", style = MaterialTheme.typography.caption)
        }
    }
}


@Composable
fun WeatherStateImage(imageUrl: String) {
    Log.d("TAG", "WeatherStateImage:Here is the image url $imageUrl")
    Image(
        painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .error(R.drawable.rain)
                .build()
        ),
        contentDescription = "Icon image",
        modifier = Modifier.size(100.dp)
    )
}
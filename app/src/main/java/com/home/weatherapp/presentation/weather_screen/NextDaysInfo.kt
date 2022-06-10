package com.home.weatherapp.presentation.weather_screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.home.weatherapp.R
import com.home.weatherapp.ui.theme.SpecificTextColor
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "NextDaysInfo"

@Composable
fun NextDaysInfo(
    state: WeatherScreenState
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(state.weatherData.first().days.size) {

//            if(it == 0){
//                return@items
//            }

            val day = state.weatherData.first().days[it]
            val icon: Int = when (day.icon) {
                "snow" -> {
                    R.drawable.snow
                }
                "snow-showers-day" -> {
                    R.drawable.snowshowersday
                }
                "snow-showers-night" -> {
                    R.drawable.snowshowersnight
                }
                "thunder-rain" -> {
                    R.drawable.thunderrain
                }
                "thunder-showers-day" -> {
                    R.drawable.thundershowersday
                }
                "thunder-showers-night" -> {
                    R.drawable.thundershowersnight
                }
                "rain" -> {
                    R.drawable.rain
                }
                "showers-day" -> {
                    R.drawable.showersday
                }
                "showers-night" -> {
                    R.drawable.showersnight
                }
                "fog" -> {
                    R.drawable.fog
                }
                "wind" -> {
                    R.drawable.wind
                }
                "cloudy" -> {
                    R.drawable.cloudy
                }
                "partly-cloudy-day" -> {
                    R.drawable.partlycloudyday
                }
                "partly-cloudy-night" -> {
                    R.drawable.partlycloudynight
                }
                "clear-day" -> {
                    R.drawable.clearday
                }
                "clear-night" -> {
                    R.drawable.clearnight
                }
                else -> {
                    -1
                }
            }


            val inFormat = SimpleDateFormat("dd-MM-yyyy")
            val date = inFormat.parse(day.datetime)
            val outFormat = SimpleDateFormat("EEEE", Locale.US)
            val goal = outFormat.format(date).replaceFirstChar { it.uppercase() }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                Arrangement.SpaceBetween
            ) {

                Text(
                    text = goal,
                    modifier = Modifier.align(CenterVertically)
                )
                Icon(
                    painterResource(
                        id = icon,
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(35.dp)
                        .align(CenterVertically)
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(text = buildAnnotatedString {
                    append(text = "${day.temp}° ")
                    withStyle(
                        style = SpanStyle(
                            color = SpecificTextColor
                        )
                    ) {
                        append("${day.feelslike}°")
                    }
                })
            }
        }
    }
}
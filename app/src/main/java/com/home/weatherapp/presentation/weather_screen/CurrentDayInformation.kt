package com.home.weatherapp.presentation.weather_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.home.weatherapp.R
import com.home.weatherapp.util.formatters.formatEpochTimesToTime
import com.home.weatherapp.ui.theme.IconColor
import com.home.weatherapp.ui.theme.LighterBlue
import com.home.weatherapp.ui.theme.SpecificTextColor
import com.home.weatherapp.util.getIcon
import kotlin.math.roundToInt

@Composable
fun CurrentDayInfo(
    state: WeatherScreenState
) {
    val weatherData = state.weatherData.first()
    val currentConditions = weatherData.currentConditions

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(200.dp),
        Arrangement.SpaceEvenly,
        Alignment.CenterHorizontally
    ) {
        Text(
            text = weatherData.address.replaceFirstChar { it.uppercase() },
            fontSize = 20.sp,
            fontWeight = FontWeight.Light,
            fontFamily = FontFamily.Serif
        )
        Text(
            text = "${currentConditions.temperature} \u2103",
            fontSize = 36.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Serif
        )
        Text(
            text = currentConditions.icon
                .replaceFirstChar { it.uppercase() }
                .replace("-", " "),
            modifier = Modifier
                .clip(RoundedCornerShape(100.dp))
                .background(color = LighterBlue)
                .padding(8.dp)
        )
        Text(
            text = weatherData.description,
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            fontFamily = FontFamily.Serif
        )

        Spacer(modifier = Modifier.height(36.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            Arrangement.SpaceEvenly,
            Alignment.CenterVertically
        ) {

            Row(
                modifier = Modifier.wrapContentWidth(),
                Arrangement.Start,
                Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_water_drop
                    ),
                    contentDescription = null,
                    tint = IconColor
                )
                Text(
                    text = currentConditions.precipProb ?: "No value",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Row(
                modifier = Modifier.wrapContentWidth(),
                Arrangement.Start,
                Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.atmospheric_pressure_icon,
                    ),
                    contentDescription = null,
                    tint = IconColor,
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .size(25.dp)
                )
                Text(
                    text = "${currentConditions.pressure / 1000} Bar",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Row(
                modifier = Modifier.wrapContentWidth(),
                Arrangement.Start,
                Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.wind_gust_icon,
                    ),
                    contentDescription = null,
                    tint = IconColor,
                    modifier = Modifier
                        .size(35.dp)
                )
                Text(
                    text = "${(currentConditions.windspeed / 3.6).roundToInt()} m/s",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            Arrangement.SpaceAround,
            Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.wrapContentSize()) {
                Text(text = "Sunrise")
                Text(text = formatEpochTimesToTime(currentConditions.sunrise.toLong()))
            }
            Column(modifier = Modifier.wrapContentSize()) {
                Text(text = "Sunset")
                Text(text = formatEpochTimesToTime(currentConditions.sunset.toLong()))
            }

        }

//        SunriseSunsetChart(state)

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Today",
            modifier = Modifier.align(Start),
            color = SpecificTextColor
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            state = listState
        ) {

            items(state.weatherData.first().days.first().hour.size) {

                val hour = state.weatherData.first().days.first().hour[it]

                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(8.dp),
                    Arrangement.SpaceEvenly,
                    Alignment.CenterHorizontally
                ) {
                    Text(text = formatEpochTimesToTime(hour.datetimeEpoch))
                    Image(
                        imageVector = ImageVector.vectorResource(id = getIcon(hour.icon)),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                    Text(text = "${hour.temp} \u00B0")
                }
            }
        }
        NextDaysInfo(state)
    }
}
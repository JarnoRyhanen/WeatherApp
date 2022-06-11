package com.home.weatherapp.presentation.weather_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.home.weatherapp.R
import com.home.weatherapp.ui.theme.SpecificTextColor
import com.home.weatherapp.util.formatters.formatDateToDay
import com.home.weatherapp.util.getIcon
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

            if(it == 0){
                return@items
            }

            val day = state.weatherData.first().days[it]

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                Arrangement.SpaceBetween
            ) {

                Text(
                    text = formatDateToDay(day.datetime),
                    modifier = Modifier.align(CenterVertically)
                )
                Image(
                    imageVector = ImageVector.vectorResource(
                        id = getIcon(day.icon),
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
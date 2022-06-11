package com.home.weatherapp.util

import com.home.weatherapp.R

fun getIcon(icon: String): Int {
    return when (icon) {
        "snow" -> {
            R.drawable.ic_snow
        }
        "snow-showers-day" -> {
            R.drawable.ic_snow_showers_day
        }
        "snow-showers-night" -> {
            R.drawable.ic_snow_showers_night
        }
        "thunder-rain" -> {
            R.drawable.ic_thunder_rain
        }
        "thunder-showers-day" -> {
            R.drawable.ic_thunder_showers_day
        }
        "thunder-showers-night" -> {
            R.drawable.ic_thunder_showers_night
        }
        "rain" -> {
            R.drawable.ic_rain
        }
        "showers-day" -> {
            R.drawable.ic_showers_day
        }
        "showers-night" -> {
            R.drawable.ic_showers_night
        }
        "fog" -> {
            R.drawable.ic_fog
        }
        "wind" -> {
            R.drawable.ic_wind
        }
        "cloudy" -> {
            R.drawable.ic_cloudy
        }
        "partly-cloudy-day" -> {
            R.drawable.ic_partly_cloudy_day
        }
        "partly-cloudy-night" -> {
            R.drawable.ic_partly_cloudy_night
        }
        "clear-day" -> {
            R.drawable.ic_clear_day
        }
        "clear-night" -> {
            R.drawable.ic_clear_night
        }
        else -> {
            -1
        }
    }
}
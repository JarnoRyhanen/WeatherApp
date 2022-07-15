package com.home.weatherapp.appsettings

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
 val isFirstTime: FirstTime = FirstTime.TRUE
)

enum class FirstTime {
    FALSE, TRUE
}
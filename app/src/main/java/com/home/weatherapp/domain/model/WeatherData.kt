package com.home.weatherapp.domain.model

data class WeatherData(
    val address: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val resolvedAddress: String,
    val timezone: String,
    val timezoneOffset: Double
)
package com.home.weatherapp.data.remote.dto

data class WeatherDto(
    val address: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val queryCost: Int,
    val resolvedAddress: String,
    val timezone: String,
    val tzoffset: Double
)
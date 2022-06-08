package com.home.weatherapp.data.remote.dto

data class WeatherDto(
    val address: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val queryCost: Int,
    val resolvedAddress: String,
    val timezone: String,
    val tzoffset: Double,
    val currentConditions: CurrentConditionsDto
)

data class CurrentConditionsDto(
    val datetime: String,
    val datetimeEpoch: Long,
    val temp: Double,
    val feelslike: Double,
    val windspeed: Double,
    val conditions: String,
    val icon: String,
    val sunrise: String,
    val sunset: String,
)




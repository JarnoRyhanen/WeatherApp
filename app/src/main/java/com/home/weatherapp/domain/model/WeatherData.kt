package com.home.weatherapp.domain.model

data class WeatherData(
    val address: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val resolvedAddress: String,
    val timezone: String,
    val timezoneOffset: Double,
    val currentConditions: CurrentConditions,
    val days: List<Days>
)

data class CurrentConditions(
    val dateTime: String,
    val dateTimeEpoch: Long,
    val temperature: Double,
    val feelslike: Double,
    val windspeed: Double,
    val conditions: String,
    val icon: String,
    val sunrise: String,
    val sunset: String,
    val location: String
)

data class Days(
    val locationDay: String,
    val datetime: String,
    val datetimeEpoch: Long,
    val temp: Double,
    val feelslike: Double,
    val windspeed: Double,
    val conditions: String,
    val icon: String,
    val hour: List<Hour>
)

data class Hour(
    val locationHour: String,
    val datetime: String,
    val datetimeEpoch: Long,
    val temp: Double,
    val icon: String
)
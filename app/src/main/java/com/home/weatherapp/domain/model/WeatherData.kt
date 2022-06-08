package com.home.weatherapp.domain.model

import androidx.room.PrimaryKey
import com.home.weatherapp.data.local.WeatherDataEntity

data class WeatherData(
    val address: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val resolvedAddress: String,
    val timezone: String,
    val timezoneOffset: Double
//    val currentConditions: List<>
){
//    data class CurrentConditions(
//        val dateTime: String,
//        @PrimaryKey val dateTimeEpoch: Long,
//        val temperature: Double,
//        val feelslike: Double,
//        val windspeed: Double,
//        val conditions: String,
//        val icon: String,
//        val sunrise: String,
//        val sunset: String,
//    )
}
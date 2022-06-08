package com.home.weatherapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherDataEntity(
    @PrimaryKey val address: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val resolvedAddress: String,
    val timezone: String,
    val timezoneOffset: Double
)

@Entity
data class CurrentConditionsEntity(
    @PrimaryKey val location: String,
    val dateTime: String,
    val dateTimeEpoch: Long,
    val temperature: Double,
    val feelslike: Double,
    val windspeed: Double,
    val conditions: String,
    val icon: String,
    val sunrise: String,
    val sunset: String,
)


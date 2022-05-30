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
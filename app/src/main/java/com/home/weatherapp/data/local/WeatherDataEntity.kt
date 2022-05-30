package com.home.weatherapp.data.local

import androidx.room.Entity

@Entity
data class WeatherDataEntity(
    val address: String,
    val currentConditions: CurrentConditionsEntity,
    val days: List<DayEntity>,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val resolvedAddress: String,
    val timezone: String,
    val tzoffset: Double
)
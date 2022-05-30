package com.home.weatherapp.domain.model

import com.home.weatherapp.data.remote.dto.CurrentConditionsDto
import com.home.weatherapp.data.remote.dto.DayDto

data class WeatherData(
    val address: String,
    val currentConditions: CurrentConditions,
    val days: List<Day>,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val resolvedAddress: String,
    val timezone: String,
    val tzoffset: Double
)
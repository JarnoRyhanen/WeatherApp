package com.home.weatherapp.presentation.weather_screen

import com.home.weatherapp.domain.model.WeatherData

data class WeatherScreenState(
    val weatherData: List<WeatherData> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = "Helsinki"
)

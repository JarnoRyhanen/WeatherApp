package com.home.weatherapp.presentation.weather_screen

sealed class WeatherScreenEvent {

    object Refresh: WeatherScreenEvent()
    data class OnSearchQueryChange(val query: String): WeatherScreenEvent()
}

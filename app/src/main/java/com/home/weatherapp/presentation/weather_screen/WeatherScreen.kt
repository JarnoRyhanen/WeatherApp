package com.home.weatherapp.presentation.weather_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

private const val TAG = "WeatherScreen"

@Preview
@Composable
fun WeatherScreen(
    viewModel: WeatherScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state

    if (!state.isLoading) {
        CurrentDayInfo(state)
    }
}
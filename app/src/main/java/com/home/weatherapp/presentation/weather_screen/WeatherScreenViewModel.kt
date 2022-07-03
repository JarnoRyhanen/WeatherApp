package com.home.weatherapp.presentation.weather_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.home.weatherapp.domain.repository.WeatherRepository
import com.home.weatherapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "WeatherScreenViewModel"

@HiltViewModel
class WeatherScreenViewModel @Inject constructor(
    private val repository: WeatherRepository,
) : ViewModel() {

    var weatherScreenState by mutableStateOf(WeatherScreenState())

    fun onEvent(event: WeatherScreenEvent) {
        when (event) {
            is WeatherScreenEvent.Refresh -> {
                getWeatherData(fetchFromRemote = true)
            }
        }
    }

    init {
        viewModelScope.launch {
            Log.d(TAG, "i am in init: ")
            getWeatherData(fetchFromRemote = false)
        }
    }

    private fun getWeatherData(
        query: String = if(weatherScreenState.weatherData.isNotEmpty()) weatherScreenState.weatherData.first().address else "",
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            repository
                .getWeatherData(fetchFromRemote, query)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let {
                                weatherScreenState = weatherScreenState.copy(
                                    weatherData = it
                                )
                            }
                        }
                        is Resource.Error -> {
                            Log.d(TAG, "getWeatherData: $result")
                        }
                        is Resource.Loading -> {
                            weatherScreenState =
                                weatherScreenState.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }

}
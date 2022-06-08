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
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

private const val TAG = "WeatherScreenViewModel"

@HiltViewModel
class WeatherScreenViewModel @Inject constructor(
    private val repository: WeatherRepository,
) : ViewModel() {

    var state by mutableStateOf(WeatherScreenState())

    private var searchJob: Job? = null

    fun onEvent(event: WeatherScreenEvent) {
        when (event) {
            is WeatherScreenEvent.Refresh -> {
                getWeatherData(fetchFromRemote = true)
            }

            is WeatherScreenEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(100L)
                    getWeatherData()
                }
            }
        }
    }

    init {
        getWeatherData(fetchFromRemote = true)
    }

    private fun getWeatherData(
        query: String = state.searchQuery.lowercase(),
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            repository
                .getWeatherData(fetchFromRemote, query)
                .collect { result ->
                    when (result){
                        is Resource.Success -> {
                            result.data?.let {
                                state = state.copy(
                                    weatherData = it
                                )
                            }
                        }
                        is Resource.Error -> {}
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }

}
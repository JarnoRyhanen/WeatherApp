package com.home.weatherapp.presentation.map_screen

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

private const val TAG = "MapsScreenViewModel"

@HiltViewModel
class MapsScreenViewModel @Inject constructor(
    private val repository: WeatherRepository,
) : ViewModel() {

    var state by mutableStateOf(MapState())

    fun onEvent(event: MapEvent) {
        when (event) {
            is MapEvent.OnMapLongClick -> {
                viewModelScope.launch {
                    state = state.copy(
                        searchQuery = event.location
                    )
                    repository.getWeatherData(
                        true,
                        state.searchQuery
                    ).collect{ result ->
                        when (result){
                            is Resource.Success -> {
                            }
                            is Resource.Loading -> {
                            }
                            is Resource.Error -> {
                            }
                        }
                    }
                }
            }
            MapEvent.OnInfoWindowLongClick -> {

            }
        }
    }

}



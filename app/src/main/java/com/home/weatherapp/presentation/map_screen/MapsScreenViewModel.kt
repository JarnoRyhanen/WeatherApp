package com.home.weatherapp.presentation.map_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.home.weatherapp.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapsScreenViewModel @Inject constructor(
    private val repository: WeatherRepository,
) : ViewModel() {

    var state by mutableStateOf(MapState())

    fun onEvent(event: MapEvent) {
        when (event) {
            MapEvent.selectLocation -> {

            }
        }
    }

}



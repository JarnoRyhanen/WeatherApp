package com.home.weatherapp.presentation.map_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.home.weatherapp.domain.repository.WeatherRepository
import com.home.weatherapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MapsScreenViewModel"

@HiltViewModel
class MapsScreenViewModel @Inject constructor(
    private val repository: WeatherRepository,
) : ViewModel() {

    var state by mutableStateOf(MapState())

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()

    private val _dialogLocation = MutableStateFlow("")
    val dialogLocation: StateFlow<String> = _dialogLocation.asStateFlow()

    fun onOpenDialogClicked(location: String) {
        _showDialog.value = true
        _dialogLocation.value = location
    }

    fun onDialogConfirm() {
        _showDialog.value = false
        onEvent(MapEvent.OnDialogConfirm(dialogLocation.value))
    }

    fun onDialogDismiss() {
        _showDialog.value = false
    }

    private fun onEvent(event: MapEvent) {
        when (event) {
            is MapEvent.OnMapLongClick -> {

            }
            is MapEvent.OnDialogConfirm -> {
                viewModelScope.launch {
                    state = state.copy(
                        searchQuery = event.location
                    )
                    repository.getWeatherData(
                        true,
                        state.searchQuery
                    ).collect { result ->
                        when (result) {
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
        }
    }

}



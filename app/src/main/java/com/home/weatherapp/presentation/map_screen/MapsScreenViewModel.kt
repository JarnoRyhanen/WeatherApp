package com.home.weatherapp.presentation.map_screen

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapsScreenViewModel @Inject constructor(

) : ViewModel() {

    var state by mutableStateOf(MapState())

}



package com.home.weatherapp.presentation.map_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun MapScreen(
    viewModel: MapsScreenViewModel = hiltViewModel()
) {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Red)) {

    }
    
    
//    val scaffoldState = rememberScaffoldState()
//    val uiSettings = remember {
//        MapUiSettings(zoomControlsEnabled = false)
//    }
//    Scaffold(
//        scaffoldState = scaffoldState
//    ) {
//        GoogleMap(
//            modifier = Modifier.fillMaxSize(),
//            properties = viewModel.state.properties,
//            uiSettings = uiSettings,
//            onMapLongClick = {
//
//            }
//        )
//
//    }
//
}
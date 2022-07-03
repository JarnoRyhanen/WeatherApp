package com.home.weatherapp.presentation.map_screen

import android.location.Geocoder
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings

private const val TAG = "MapScreen"

@Composable
fun MapScreen(
    viewModel: MapsScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val scaffoldState = rememberScaffoldState()
    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = true)
    }


    Scaffold(
        scaffoldState = scaffoldState,
        content = {
            it
            GoogleMap(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(bottom = 75.dp),
                properties = viewModel.state.properties,
                uiSettings = uiSettings,
                onMapLongClick = { coordinates ->

                    val location = Geocoder(context).getFromLocation(
                        coordinates.latitude,
                        coordinates.longitude,
                        1
                    ).first().locality

                    if (location != null) {
                        viewModel.onEvent(MapEvent.OnMapLongClick(location))
                    }

                },
                cameraPositionState = CameraPositionState(
                    CameraPosition(
                        LatLng(60.1712, 24.9327), 12f, 0f, 0f
                    )
                )
            )
        }
    )
}
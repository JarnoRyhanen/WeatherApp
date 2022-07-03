package com.home.weatherapp.presentation.map_screen

import android.location.Geocoder
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.home.weatherapp.ui.theme.DeepBlue
import com.home.weatherapp.ui.theme.TextWhite
import kotlinx.coroutines.launch

private const val TAG = "MapScreen"

@Composable
fun MapScreen(
    viewModel: MapsScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    val scaffoldState = rememberScaffoldState()
    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = true)
    }
    val showDialogState: Boolean by viewModel.showDialog.collectAsState()
    val location: String by viewModel.dialogLocation.collectAsState()

    PopUpAlertDialog(
        show = showDialogState,
        onDismiss = viewModel::onDialogDismiss,
        onConfirm = viewModel::onDialogConfirm,
        location = location
    )

    Scaffold(
        scaffoldState = scaffoldState,
        content = {
            it
            GoogleMap(
                modifier = Modifier
                    .wrapContentSize(),
                properties = viewModel.state.properties,
                uiSettings = uiSettings,
                onMapLongClick = { coordinates ->

                    val location = Geocoder(context).getFromLocation(
                        coordinates.latitude,
                        coordinates.longitude,
                        1
                    ).first().locality

                    if (location != null) {
                        viewModel.onOpenDialogClicked(location)
                        return@GoogleMap
                    }
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            "Unable to get weather for this location, try somewhere else."
                        )
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

@Composable
fun PopUpAlertDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    location: String
) {

    if (show) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(text = "Confirm?", color = TextWhite) },
            text = {
                Text(
                    text = "Do you want to get weather for $location",
                    color = TextWhite
                )
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text(text = "Dismiss", color = TextWhite)
                }
            },
            confirmButton = {
                TextButton(onClick = onConfirm) {
                    Text(text = "Confirm", color = TextWhite)
                }
            },
            backgroundColor = DeepBlue,
            contentColor = Color.White
        )
    }
}
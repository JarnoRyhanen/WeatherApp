package com.home.weatherapp.presentation.map_screen

import com.google.android.gms.maps.model.LatLng

sealed class MapEvent {

    data class OnMapLongClick(val location: String): MapEvent()
    object OnInfoWindowLongClick: MapEvent()

}

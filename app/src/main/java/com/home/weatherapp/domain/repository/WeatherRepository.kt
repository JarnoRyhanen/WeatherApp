package com.home.weatherapp.domain.repository

import com.home.weatherapp.domain.model.WeatherData
import com.home.weatherapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getWeatherData(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<WeatherData>>>


}
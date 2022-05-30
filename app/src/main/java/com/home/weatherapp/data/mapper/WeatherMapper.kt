package com.home.weatherapp.data.mapper

import com.home.weatherapp.data.local.WeatherDataEntity
import com.home.weatherapp.data.remote.dto.WeatherDto
import com.home.weatherapp.domain.model.WeatherData

fun WeatherDataEntity.toWeatherData(): WeatherData {
    return WeatherData(
        address = address,
        description = description,
        latitude = latitude,
        longitude = longitude,
        resolvedAddress = resolvedAddress,
        timezone = timezone,
        timezoneOffset = timezoneOffset
    )
}

fun WeatherData.toWeatherDataEntity(): WeatherDataEntity {
    return WeatherDataEntity(
        address = address,
        description = description,
        latitude = latitude,
        longitude = longitude,
        resolvedAddress = resolvedAddress,
        timezone = timezone,
        timezoneOffset = timezoneOffset
    )
}

fun WeatherDto.toWeatherDataEntity(): WeatherDataEntity {
    return WeatherDataEntity(
        address = address,
        description = description,
        latitude = latitude,
        longitude = longitude,
        resolvedAddress = resolvedAddress,
        timezone = timezone,
        timezoneOffset = tzoffset
    )
}
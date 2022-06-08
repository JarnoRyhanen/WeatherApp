package com.home.weatherapp.data.mapper

import com.home.weatherapp.data.local.CurrentConditionsEntity
import com.home.weatherapp.data.local.WeatherDataEntity
import com.home.weatherapp.data.remote.dto.CurrentConditionsDto
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
        timezoneOffset = timezoneOffset,
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
        timezoneOffset = timezoneOffset,
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


fun CurrentConditionsDto.toCurrentConditionsEntity(query: String) : CurrentConditionsEntity {
    return CurrentConditionsEntity(
        dateTime = datetime,
        dateTimeEpoch = datetimeEpoch,
        temperature = temp,
        feelslike = feelslike,
        windspeed = windspeed,
        conditions = conditions,
        icon = icon,
        sunset = sunset,
        sunrise = sunrise,
        location = query
    )
}

//fun CurrentConditionsEntity.toCurrentConditions() : CurrentConditionsEntity {
//    return CurrentConditionsEntity(
//        dateTime = dateTime,
//        dateTimeEpoch = dateTimeEpoch,
//        temperature = temperature,
//        feelslike = feelslike,
//        windspeed = windspeed,
//        conditions = conditions,
//        icon = icon,
//        sunset = sunset,
//        sunrise = sunrise
//    )
//}
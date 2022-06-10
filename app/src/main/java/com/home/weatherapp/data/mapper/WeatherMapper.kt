package com.home.weatherapp.data.mapper

import com.home.weatherapp.data.local.CurrentConditionsEntity
import com.home.weatherapp.data.local.DaysEntity
import com.home.weatherapp.data.local.HourEntity
import com.home.weatherapp.data.local.WeatherDataEntity
import com.home.weatherapp.data.remote.dto.CurrentConditionsDto
import com.home.weatherapp.data.remote.dto.DaysDto
import com.home.weatherapp.data.remote.dto.HoursDto
import com.home.weatherapp.data.remote.dto.WeatherDto
import com.home.weatherapp.domain.model.CurrentConditions
import com.home.weatherapp.domain.model.Days
import com.home.weatherapp.domain.model.Hour
import com.home.weatherapp.domain.model.WeatherData

fun WeatherDto.toWeatherDataEntity(location: String): WeatherDataEntity {
    return WeatherDataEntity(
        address = address,
        description = description,
        latitude = latitude,
        longitude = longitude,
        resolvedAddress = resolvedAddress,
        timezone = timezone,
        timezoneOffset = tzoffset,
        days = days.map { it.toDayEntity(location) },
        currentConditions = currentConditions.toCurrentConditionsEntity(location)
    )
}

fun WeatherDataEntity.toWeatherData(location: String): WeatherData {
    return WeatherData(
        address = address,
        description = description,
        latitude = latitude,
        longitude = longitude,
        resolvedAddress = resolvedAddress,
        timezone = timezone,
        timezoneOffset = timezoneOffset,
        currentConditions = currentConditions.toCurrentConditions(location),
        days = days.map { it.toDays(location) },
    )
}

fun WeatherData.toWeatherDataEntity(location: String): WeatherDataEntity {
    return WeatherDataEntity(
        address = address,
        description = description,
        latitude = latitude,
        longitude = longitude,
        resolvedAddress = resolvedAddress,
        timezone = timezone,
        timezoneOffset = timezoneOffset,
        currentConditions = currentConditions.toCurrentConditionsEntity(location),
        days = days.map { it.toDayEntity(location) }
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
        precipProb = precipprob,
        pressure = pressure
    )
}

fun CurrentConditionsEntity.toCurrentConditions(location: String) : CurrentConditions {
    return CurrentConditions(
        dateTime = dateTime,
        dateTimeEpoch = dateTimeEpoch,
        temperature = temperature,
        feelslike = feelslike,
        windspeed = windspeed,
        conditions = conditions,
        icon = icon,
        sunset = sunset,
        sunrise = sunrise,
        location = location,
        precipProb = precipProb,
        pressure = pressure
    )
}

fun CurrentConditions.toCurrentConditionsEntity(location: String) : CurrentConditionsEntity {
    return CurrentConditionsEntity(
        dateTime = dateTime,
        dateTimeEpoch = dateTimeEpoch,
        temperature = temperature,
        feelslike = feelslike,
        windspeed = windspeed,
        conditions = conditions,
        icon = icon,
        sunset = sunset,
        sunrise = sunrise,
        precipProb = precipProb,
        pressure = pressure
    )
}

fun DaysDto.toDayEntity(location: String) : DaysEntity {
    return DaysEntity(
        datetime = datetime,
        datetimeEpoch = datetimeEpoch,
        temp = temp,
        feelslike = feelslike,
        windspeed = windspeed,
        conditions = conditions,
        icon = icon,
        locationDay = location,
        hours = hours.map { it.toHourEntity(location) }
    )
}

fun DaysEntity.toDays(location: String) : Days {
    return Days(
        datetime = datetime,
        datetimeEpoch = datetimeEpoch,
        temp = temp,
        feelslike = feelslike,
        windspeed = windspeed,
        conditions = conditions,
        icon = icon,
        locationDay = location,
        hour = hours.map { it.toHour(location) }
    )
}

fun Days.toDayEntity(location: String) : DaysEntity {
    return DaysEntity(
        datetime = datetime,
        datetimeEpoch = datetimeEpoch,
        temp = temp,
        feelslike = feelslike,
        windspeed = windspeed,
        conditions = conditions,
        icon = icon,
        locationDay = location,
        hours = hour.map { it.toHourEntity(location) }
    )
}
fun HoursDto.toHourEntity(location: String) : HourEntity{
    return HourEntity(
        datetime = datetime,
        datetimeEpoch = datetimeEpoch,
        temp = temp,
        icon = icon,
        locationHour = location
    )
}

fun HourEntity.toHour(location: String) : Hour{
    return Hour(
        datetime = datetime,
        datetimeEpoch = datetimeEpoch,
        temp = temp,
        icon = icon,
        locationHour = location
    )
}

fun Hour.toHourEntity(location: String) : HourEntity{
    return HourEntity(
        datetime = datetime,
        datetimeEpoch = datetimeEpoch,
        temp = temp,
        icon = icon,
        locationHour = location
    )
}

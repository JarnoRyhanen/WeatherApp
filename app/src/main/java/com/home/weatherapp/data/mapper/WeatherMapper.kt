package com.home.weatherapp.data.mapper

import com.home.weatherapp.data.local.CurrentConditionsEntity
import com.home.weatherapp.data.local.DayEntity
import com.home.weatherapp.data.local.HourEntity
import com.home.weatherapp.data.local.WeatherDataEntity
import com.home.weatherapp.data.remote.dto.CurrentConditionsDto
import com.home.weatherapp.data.remote.dto.DayDto
import com.home.weatherapp.data.remote.dto.HourDto
import com.home.weatherapp.data.remote.dto.WeatherDto
import com.home.weatherapp.domain.model.CurrentConditions
import com.home.weatherapp.domain.model.Day
import com.home.weatherapp.domain.model.Hour
import com.home.weatherapp.domain.model.WeatherData

fun WeatherDataEntity.toWeatherData(): WeatherData {
    return WeatherData(
        address = address,
        currentConditions = currentConditions.toCurrentConditions(),
        days = days.map {
            it.toDay()
        },
        description = description,
        latitude = latitude,
        longitude = longitude,
        resolvedAddress = resolvedAddress,
        timezone = timezone,
        tzoffset = tzoffset
    )
}

fun CurrentConditionsEntity.toCurrentConditions(): CurrentConditions {
    return CurrentConditions(
        cloudcover = cloudcover,
        conditions = conditions,
        datetime = datetime,
        datetimeEpoch = datetimeEpoch,
        dew = dew,
        feelslike = feelslike,
        humidity = humidity,
        icon = icon,
        moonphase = moonphase,
        precip = precip,
        precipprob = precipprob,
        preciptype = preciptype,
        pressure = pressure,
        snow = snow,
        snowdepth = snowdepth,
        solarenergy = solarenergy,
        solarradiation = solarradiation,
        sunrise = sunrise,
        sunriseEpoch = sunriseEpoch,
        sunset = sunset,
        sunsetEpoch = sunsetEpoch,
        temp = temp,
        uvindex = uvindex,
        visibility = visibility,
        winddir = winddir,
        windgust = windgust,
        windspeed = windspeed
    )
}

fun DayEntity.toDay(): Day {
    return Day(
        cloudcover = cloudcover,
        conditions = conditions,
        datetime = datetime,
        datetimeEpoch = datetimeEpoch,
        dew = dew,
        feelslike = feelslike,
        humidity = humidity,
        icon = icon,
        moonphase = moonphase,
        precip = precip,
        precipprob = precipprob,
        preciptype = preciptype,
        pressure = pressure,
        snow = snow,
        snowdepth = snowdepth,
        solarenergy = solarenergy,
        solarradiation = solarradiation,
        sunrise = sunrise,
        sunriseEpoch = sunriseEpoch,
        sunset = sunset,
        sunsetEpoch = sunsetEpoch,
        temp = temp,
        uvindex = uvindex,
        visibility = visibility,
        winddir = winddir,
        windgust = windgust,
        windspeed = windspeed,
        feelslikemax = feelslikemax,
        feelslikemin = feelslikemin,
        tempmax = tempmax,
        tempmin = tempmin,
        source = source,
        severerisk = severerisk,
        hours = hours.map {
            it.toHour()
        },
        precipcover = precipcover,
        description = description
    )
}

fun HourEntity.toHour(): Hour {
    return Hour(
        cloudcover = cloudcover,
        conditions = conditions,
        datetime = datetime,
        datetimeEpoch = datetimeEpoch,
        dew = dew,
        feelslike = feelslike,
        humidity = humidity,
        icon = icon,
        precip = precip,
        precipprob = precipprob,
        preciptype = preciptype,
        pressure = pressure,
        snow = snow,
        snowdepth = snowdepth,
        solarenergy = solarenergy,
        solarradiation = solarradiation,
        temp = temp,
        uvindex = uvindex,
        visibility = visibility,
        winddir = winddir,
        windgust = windgust,
        windspeed = windspeed,
        source = source,
        severerisk = severerisk,
    )
}


fun WeatherData.toWeatherDataEntity(): WeatherDataEntity {
    return WeatherDataEntity(
        address = address,
        currentConditions = currentConditions.toCurrentConditionsEntity(),
        days = days.map {
            it.toDayEntity()
        },
        description = description,
        latitude = latitude,
        longitude = longitude,
        resolvedAddress = resolvedAddress,
        timezone = timezone,
        tzoffset = tzoffset
    )
}

fun CurrentConditions.toCurrentConditionsEntity(): CurrentConditionsEntity {
    return CurrentConditionsEntity(
        cloudcover = cloudcover,
        conditions = conditions,
        datetime = datetime,
        datetimeEpoch = datetimeEpoch,
        dew = dew,
        feelslike = feelslike,
        humidity = humidity,
        icon = icon,
        moonphase = moonphase,
        precip = precip,
        precipprob = precipprob,
        preciptype = preciptype,
        pressure = pressure,
        snow = snow,
        snowdepth = snowdepth,
        solarenergy = solarenergy,
        solarradiation = solarradiation,
        sunrise = sunrise,
        sunriseEpoch = sunriseEpoch,
        sunset = sunset,
        sunsetEpoch = sunsetEpoch,
        temp = temp,
        uvindex = uvindex,
        visibility = visibility,
        winddir = winddir,
        windgust = windgust,
        windspeed = windspeed
    )
}

fun Day.toDayEntity(): DayEntity {
    return DayEntity(
        cloudcover = cloudcover,
        conditions = conditions,
        datetime = datetime,
        datetimeEpoch = datetimeEpoch,
        dew = dew,
        feelslike = feelslike,
        humidity = humidity,
        icon = icon,
        moonphase = moonphase,
        precip = precip,
        precipprob = precipprob,
        preciptype = preciptype,
        pressure = pressure,
        snow = snow,
        snowdepth = snowdepth,
        solarenergy = solarenergy,
        solarradiation = solarradiation,
        sunrise = sunrise,
        sunriseEpoch = sunriseEpoch,
        sunset = sunset,
        sunsetEpoch = sunsetEpoch,
        temp = temp,
        uvindex = uvindex,
        visibility = visibility,
        winddir = winddir,
        windgust = windgust,
        windspeed = windspeed,
        feelslikemax = feelslikemax,
        feelslikemin = feelslikemin,
        tempmax = tempmax,
        tempmin = tempmin,
        source = source,
        severerisk = severerisk,
        hours = hours.map {
            it.toHourEntity()
        },
        precipcover = precipcover,
        description = description
    )
}

fun Hour.toHourEntity(): HourEntity {
    return HourEntity(
        cloudcover = cloudcover,
        conditions = conditions,
        datetime = datetime,
        datetimeEpoch = datetimeEpoch,
        dew = dew,
        feelslike = feelslike,
        humidity = humidity,
        icon = icon,
        precip = precip,
        precipprob = precipprob,
        preciptype = preciptype,
        pressure = pressure,
        snow = snow,
        snowdepth = snowdepth,
        solarenergy = solarenergy,
        solarradiation = solarradiation,
        temp = temp,
        uvindex = uvindex,
        visibility = visibility,
        winddir = winddir,
        windgust = windgust,
        windspeed = windspeed,
        source = source,
        severerisk = severerisk,
    )
}

fun WeatherDto.toWeatherDataEntity(): WeatherDataEntity {
    return WeatherDataEntity(
        address = address,
        currentConditions = currentConditions.toCurrentConditionsEntity(),
        days = days.map {
                        it.toDayEntity()
        },
        description = description,
        latitude = latitude,
        longitude = longitude,
        resolvedAddress = resolvedAddress,
        timezone = timezone,
        tzoffset = tzoffset
    )
}

fun CurrentConditionsDto.toCurrentConditionsEntity(): CurrentConditionsEntity {
    return CurrentConditionsEntity(
        cloudcover = cloudcover,
        conditions = conditions,
        datetime = datetime,
        datetimeEpoch = datetimeEpoch,
        dew = dew,
        feelslike = feelslike,
        humidity = humidity,
        icon = icon,
        moonphase = moonphase,
        precip = precip,
        precipprob = precipprob,
        preciptype = preciptype,
        pressure = pressure,
        snow = snow,
        snowdepth = snowdepth,
        solarenergy = solarenergy,
        solarradiation = solarradiation,
        sunrise = sunrise,
        sunriseEpoch = sunriseEpoch,
        sunset = sunset,
        sunsetEpoch = sunsetEpoch,
        temp = temp,
        uvindex = uvindex,
        visibility = visibility,
        winddir = winddir,
        windgust = windgust,
        windspeed = windspeed
    )
}

fun DayDto.toDayEntity(): DayEntity {
    return DayEntity(
        cloudcover = cloudcover,
        conditions = conditions,
        datetime = datetime,
        datetimeEpoch = datetimeEpoch,
        dew = dew,
        feelslike = feelslike,
        humidity = humidity,
        icon = icon,
        moonphase = moonphase,
        precip = precip,
        precipprob = precipprob,
        preciptype = preciptype,
        pressure = pressure,
        snow = snow,
        snowdepth = snowdepth,
        solarenergy = solarenergy,
        solarradiation = solarradiation,
        sunrise = sunrise,
        sunriseEpoch = sunriseEpoch,
        sunset = sunset,
        sunsetEpoch = sunsetEpoch,
        temp = temp,
        uvindex = uvindex,
        visibility = visibility,
        winddir = winddir,
        windgust = windgust,
        windspeed = windspeed,
        feelslikemax = feelslikemax,
        feelslikemin = feelslikemin,
        tempmax = tempmax,
        tempmin = tempmin,
        source = source,
        severerisk = severerisk,
        hours = hours.map {
            it.toHourEntity()
        },
        precipcover = precipcover,
        description = description
    )
}

fun HourDto.toHourEntity(): HourEntity {
    return HourEntity(
        cloudcover = cloudcover,
        conditions = conditions,
        datetime = datetime,
        datetimeEpoch = datetimeEpoch,
        dew = dew,
        feelslike = feelslike,
        humidity = humidity,
        icon = icon,
        precip = precip,
        precipprob = precipprob,
        preciptype = preciptype,
        pressure = pressure,
        snow = snow,
        snowdepth = snowdepth,
        solarenergy = solarenergy,
        solarradiation = solarradiation,
        temp = temp,
        uvindex = uvindex,
        visibility = visibility,
        winddir = winddir,
        windgust = windgust,
        windspeed = windspeed,
        source = source,
        severerisk = severerisk,
    )
}
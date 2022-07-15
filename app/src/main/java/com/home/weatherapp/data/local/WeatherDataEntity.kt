package com.home.weatherapp.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "weather_data_entity")
data class WeatherDataEntity(
    @PrimaryKey val address: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val resolvedAddress: String,
    val timezone: String,
    val timezoneOffset: Double,
    val days: List<DaysEntity>,
    val currentConditions: CurrentConditionsEntity,
    val updatedAt: Long = System.currentTimeMillis()
)

data class CurrentConditionsEntity(
    val dateTime: String,
    val dateTimeEpoch: Long,
    val temperature: Double,
    val feelslike: Double,
    val windspeed: Double,
    val conditions: String,
    val icon: String,
    val precipProb: String?,
    val pressure: Double,
    val sunrise: String,
    val sunset: String
)

data class DaysEntity(
    val locationDay: String,
    val datetime: String,
    val datetimeEpoch: Long,
    val temp: Double,
    val feelslike: Double,
    val tempmax: Double,
    val tempmin: Double,
    val windspeed: Double,
    val conditions: String,
    val icon: String,
    val hours: List<HourEntity>
)

data class HourEntity(
    val locationHour: String,
    val datetime: String,
    val datetimeEpoch: Long,
    val temp: Double,
    val icon: String
)

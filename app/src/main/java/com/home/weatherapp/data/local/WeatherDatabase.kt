package com.home.weatherapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.home.weatherapp.data.converters.CurrentConditionsTypeConverter
import com.home.weatherapp.data.converters.DayTypeConverter
import com.home.weatherapp.data.converters.HourTypeConverter

@Database(
    entities = [
        WeatherDataEntity::class
    ],
    version = 2
)
@TypeConverters(
    HourTypeConverter::class,
    CurrentConditionsTypeConverter::class,
    DayTypeConverter::class,
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract val dao: WeatherDao
}
package com.home.weatherapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        WeatherDataEntity::class,
        CurrentConditionsEntity::class
//        HourEntity::class,
//        DayEntity::class
               ],
    version = 1
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract val dao: WeatherDao
}
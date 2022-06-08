package com.home.weatherapp.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.home.weatherapp.data.local.DaysEntity
import com.home.weatherapp.data.local.HourEntity

class HourTypeConverter {

    @TypeConverter
    fun listToJson(value: List<HourEntity>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<HourEntity>::class.java).toList()

}

package com.home.weatherapp.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.home.weatherapp.data.local.DaysEntity

class DayTypeConverter {

    @TypeConverter
    fun listToJson(value: List<DaysEntity>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<DaysEntity>::class.java).toList()

}
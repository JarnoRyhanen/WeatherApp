package com.home.weatherapp.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.home.weatherapp.data.local.CurrentConditionsEntity
import com.home.weatherapp.data.local.DaysEntity

class CurrentConditionsTypeConverter {

    @TypeConverter
    fun entityToJson(value: CurrentConditionsEntity?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToEntity(value: String): CurrentConditionsEntity = Gson().fromJson(value, CurrentConditionsEntity::class.java)
}
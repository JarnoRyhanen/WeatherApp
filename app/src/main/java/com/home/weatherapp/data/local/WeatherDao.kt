package com.home.weatherapp.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherData(
        weatherDataEntity: WeatherDataEntity
    )

    @Query("DELETE FROM weather_data_entity")
    suspend fun clearWeatherData()

    @Query(
        """
        SELECT * 
        FROM weather_data_entity
        WHERE LOWER(address) LIKE '%' || LOWER(:query) || '%'
    """
    )
    suspend fun searchWeatherInLocation(query: String): List<WeatherDataEntity>
}
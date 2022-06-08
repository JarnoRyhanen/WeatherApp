package com.home.weatherapp.data.local

import androidx.room.*

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherData(
        weatherDataEntity: WeatherDataEntity
    )

    @Query("DELETE FROM weatherdataentity")
    suspend fun clearWeatherData()

    @Query(
        """
        SELECT * 
        FROM weatherdataentity 
        INNER JOIN currentconditionsentity on location = address
        WHERE LOWER(address) LIKE '%' || LOWER(:query) || '%'
    """
    )
    suspend fun searchWeatherInLocation(query: String): List<WeatherDataEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentConditions(
        currentConditionsEntity: CurrentConditionsEntity
    )
    @Query("DELETE FROM currentconditionsentity")
    suspend fun clearCurrentConditions()

}
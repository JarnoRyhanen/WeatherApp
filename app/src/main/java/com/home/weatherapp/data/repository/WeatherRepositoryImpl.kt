package com.home.weatherapp.data.repository

import android.util.Log
import com.google.gson.JsonSyntaxException
import com.home.weatherapp.data.local.WeatherDatabase
import com.home.weatherapp.data.mapper.*
import com.home.weatherapp.data.remote.WeatherApi
import com.home.weatherapp.domain.model.WeatherData
import com.home.weatherapp.domain.repository.WeatherRepository
import com.home.weatherapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.log

private const val TAG = "WeatherRepositoryImpl"
@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
    private val database: WeatherDatabase
) : WeatherRepository {

    private val dao = database.dao

    override suspend fun getWeatherData(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<WeatherData>>> {
        return flow {
            emit(Resource.Loading(true))
            val localWeather = dao.searchWeatherInLocation(query)
            emit(Resource.Success(
                data = localWeather.map {
                    it.toWeatherData(query)
                }
            ))
            val isDbEmpty = localWeather.isEmpty() && query.isBlank()
            val shouldLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteWeather = try {
                val response = api.getWeatherData(query)
                listOf(response.toWeatherDataEntity(query))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("No internet connection"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Http error"))
                null
            } catch (e: JsonSyntaxException) {
                e.printStackTrace()
                emit(Resource.Error("Json syntax error"))
                null
            }

            remoteWeather?.let { weather ->
                dao.clearWeatherData()
                dao.insertWeatherData(
                    weather.first()
                )
                emit(Resource.Success(
                    data = dao
                        .searchWeatherInLocation("")
                        .map { it.toWeatherData(query) }
                ))
                emit(Resource.Loading(false))
            }
        }
    }
}
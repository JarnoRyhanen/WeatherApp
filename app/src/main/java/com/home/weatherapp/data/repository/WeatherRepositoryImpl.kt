package com.home.weatherapp.data.repository

import android.util.Log
import com.google.gson.JsonSyntaxException
import com.home.weatherapp.data.local.WeatherDatabase
import com.home.weatherapp.data.mapper.toWeatherData
import com.home.weatherapp.data.mapper.toWeatherDataEntity
import com.home.weatherapp.data.remote.WeatherApi
import com.home.weatherapp.domain.model.WeatherData
import com.home.weatherapp.domain.repository.WeatherRepository
import com.home.weatherapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

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

            val oldestTimeStamp = localWeather.firstOrNull()?.updatedAt
            val needsRefresh =
                oldestTimeStamp == null || oldestTimeStamp < System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(
                    20
                )

            val isDbEmpty = localWeather.isEmpty() && query.isBlank()
            val shouldLoadFromCache = !isDbEmpty && !fetchFromRemote
            Log.d(
                TAG,
                "getWeatherData: 2,  isdb empty $isDbEmpty  should load from cache $shouldLoadFromCache  fetchfromremote $fetchFromRemote"
            )
            Log.d(TAG, "getWeatherData: $needsRefresh   $oldestTimeStamp")

            if (shouldLoadFromCache && !needsRefresh) {
                Log.d(TAG, "getWeatherData: should load from cache")
                emit(Resource.Loading(false))
                return@flow
            }

            val address = if(localWeather.isNotEmpty()) localWeather.first().address else query

            val remoteWeather = try {
                val response = api.getWeatherData(address)
                listOf(response.toWeatherDataEntity(address))
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
                        .searchWeatherInLocation(weather.first().address)
                        .map { it.toWeatherData(query) }
                ))
            }
            emit(Resource.Loading(false))
            return@flow
            }
    }
}
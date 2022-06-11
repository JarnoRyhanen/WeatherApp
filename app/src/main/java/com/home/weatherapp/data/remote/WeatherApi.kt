package com.home.weatherapp.data.remote

import com.home.weatherapp.BuildConfig
import com.home.weatherapp.data.remote.dto.WeatherDto
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    companion object {
        const val BASE_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
        const val API_KEY = BuildConfig.API_KEY
    }

    @GET("{location}?key=$API_KEY&options=beta&contentType=json&unitGroup=metric&iconSet=icons2")
    suspend fun getWeatherData(
        @Path("location") location: String
    ) : WeatherDto

}
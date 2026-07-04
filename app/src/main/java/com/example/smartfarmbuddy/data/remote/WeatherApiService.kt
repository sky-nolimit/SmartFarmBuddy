package com.example.smartfarmbuddy.data.remote

import com.example.smartfarmbuddy.data.model.CityResponse
import com.example.smartfarmbuddy.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("v7/weather/now")
    suspend fun getCurrentWeather(
        @Query("location") location: String,
        @Query("key") key: String
    ): WeatherResponse

    @GET("v7/city/search")
    suspend fun searchCity(
        @Query("location") location: String,
        @Query("key") key: String,
        @Query("range") range: String = "cn"
    ): CityResponse
}
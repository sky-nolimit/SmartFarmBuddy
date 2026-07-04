package com.example.smartfarmbuddy.data.model

import com.squareup.moshi.Json

data class WeatherResponse(
    @field:Json(name = "code") val code: String,
    @field:Json(name = "updateTime") val updateTime: String,
    @field:Json(name = "fxLink") val fxLink: String,
    @field:Json(name = "now") val now: NowWeather,
    @field:Json(name = "refer") val refer: Refer
)

data class NowWeather(
    @field:Json(name = "obsTime") val obsTime: String,
    @field:Json(name = "temp") val temp: String,
    @field:Json(name = "feelsLike") val feelsLike: String,
    @field:Json(name = "icon") val icon: String,
    @field:Json(name = "text") val text: String,
    @field:Json(name = "windDir") val windDir: String,
    @field:Json(name = "windScale") val windScale: String,
    @field:Json(name = "windSpeed") val windSpeed: String,
    @field:Json(name = "humidity") val humidity: String,
    @field:Json(name = "precip") val precip: String,
    @field:Json(name = "pressure") val pressure: String,
    @field:Json(name = "vis") val vis: String,
    @field:Json(name = "cloud") val cloud: String,
    @field:Json(name = "dew") val dew: String
)

data class Refer(
    @field:Json(name = "sources") val sources: List<String>,
    @field:Json(name = "license") val license: List<String>
)

data class CityResponse(
    @field:Json(name = "code") val code: String,
    @field:Json(name = "location") val locations: List<City>
)

data class City(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "country") val country: String,
    @field:Json(name = "province") val province: String,
    @field:Json(name = "lat") val lat: String,
    @field:Json(name = "lon") val lon: String,
    @field:Json(name = "url") val url: String
)

data class Weather(
    val cityId: String,
    val cityName: String,
    val weatherText: String,
    val temperature: String,
    val feelsLike: String,
    val windDir: String,
    val windScale: String,
    val humidity: String,
    val updateTime: String
)

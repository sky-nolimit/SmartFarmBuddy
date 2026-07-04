package com.example.smartfarmbuddy.data.repository

import com.example.smartfarmbuddy.data.model.City
import com.example.smartfarmbuddy.data.model.Weather
import com.example.smartfarmbuddy.data.model.WeatherResponse
import com.example.smartfarmbuddy.data.remote.WeatherApiClient

class WeatherRepository {
    // 缓存的天气数据
    private var cachedWeather: Weather? = null
    
    suspend fun getWeather(cityId: String, cityName: String): Weather {
        return try {
            val response = WeatherApiClient.apiService.getCurrentWeather(
                cityId, 
                WeatherApiClient.API_KEY
            )
            
            if (response.code == "200") {
                val weather = convertToWeather(response, cityId, cityName)
                cachedWeather = weather
                weather
            } else {
                cachedWeather ?: throw Exception("获取天气失败")
            }
        } catch (e: Exception) {
            cachedWeather ?: throw e
        }
    }
    
    suspend fun searchCity(keyword: String): List<City> {
        return try {
            val response = WeatherApiClient.apiService.searchCity(
                keyword,
                WeatherApiClient.API_KEY
            )
            
            if (response.code == "200") {
                response.locations
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    private fun convertToWeather(response: WeatherResponse, cityId: String, cityName: String): Weather {
        val now = response.now
        return Weather(
            cityId = cityId,
            cityName = cityName,
            weatherText = now.text,
            temperature = now.temp,
            feelsLike = now.feelsLike,
            windDir = now.windDir,
            windScale = now.windScale,
            humidity = now.humidity,
            updateTime = response.updateTime
        )
    }
}
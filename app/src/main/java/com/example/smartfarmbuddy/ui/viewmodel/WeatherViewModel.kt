package com.example.smartfarmbuddy.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartfarmbuddy.data.model.City
import com.example.smartfarmbuddy.data.model.Weather
import com.example.smartfarmbuddy.data.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val repository = WeatherRepository()
    
    private val _weather = MutableStateFlow<Weather?>(null)
    val weather: StateFlow<Weather?> = _weather.asStateFlow()
    
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()
    
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()
    
    private val _cities = MutableStateFlow<List<City>>(emptyList())
    val cities: StateFlow<List<City>> = _cities.asStateFlow()
    
    // 当前选中的城市
    private val _selectedCity = MutableStateFlow<City>(
        City(
            id = "101170101",
            name = "银川",
            country = "中国",
            province = "宁夏",
            lat = "38.4778",
            lon = "106.2344",
            url = ""
        )
    )
    val selectedCity: StateFlow<City> = _selectedCity.asStateFlow()
    
    fun loadWeather() {
        val city = _selectedCity.value
        
        _loading.value = true
        _error.value = null
        
        viewModelScope.launch {
            try {
                val weatherData = repository.getWeather(city.id, city.name)
                _weather.value = weatherData
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }
    
    fun selectCity(city: City) {
        _selectedCity.value = city
        loadWeather()
    }
    
    fun searchCity(keyword: String) {
        viewModelScope.launch {
            val cities = repository.searchCity(keyword)
            _cities.value = cities
        }
    }
    
    fun getSelectedCity(): City {
        return _selectedCity.value
    }
}
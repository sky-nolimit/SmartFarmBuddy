package com.example.smartfarmbuddy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.smartfarmbuddy.data.CareType
import com.example.smartfarmbuddy.data.CareReminder
import com.example.smartfarmbuddy.data.Plant
import com.example.smartfarmbuddy.data.PlantCareManager
import com.example.smartfarmbuddy.data.PlantDataSource
import com.example.smartfarmbuddy.data.findPlantByName
import com.example.smartfarmbuddy.ui.components.DateDisplay
import com.example.smartfarmbuddy.ui.components.DigitalClock
import com.example.smartfarmbuddy.ui.components.FunctionButton
import com.example.smartfarmbuddy.ui.components.FunctionType
import com.example.smartfarmbuddy.ui.viewmodel.WeatherViewModel

fun createEmptyReminder(): CareReminder {
    return CareReminder("", "", false)
}

@Composable
fun HomeScreen() {
    val weatherViewModel: WeatherViewModel = viewModel()
    val weather by weatherViewModel.weather.collectAsState()
    val loading by weatherViewModel.loading.collectAsState()
    val error by weatherViewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        weatherViewModel.loadWeather()
    }

    val selectedPlantName = PlantCareManager.getSelectedPlant()
    val currentPlant = findPlantByName(selectedPlantName)

    var waterReminder by remember { mutableStateOf<CareReminder>(CareReminder("", "", false)) }
    var loosenReminder by remember { mutableStateOf<CareReminder>(CareReminder("", "", false)) }
    var fertilizeReminder by remember { mutableStateOf<CareReminder>(CareReminder("", "", false)) }

    var waterCompleted by remember { mutableStateOf(false) }
    var loosenCompleted by remember { mutableStateOf(false) }
    var fertilizeCompleted by remember { mutableStateOf(false) }

    var triggerWaterReset by remember { mutableStateOf(0) }
    var triggerLoosenReset by remember { mutableStateOf(0) }
    var triggerFertilizeReset by remember { mutableStateOf(0) }

    fun updateReminders() {
        currentPlant?.let { plant ->
            waterReminder = PlantCareManager.calculateReminder(CareType.WATER, plant)
            loosenReminder = PlantCareManager.calculateReminder(CareType.LOOSEN, plant)
            fertilizeReminder = PlantCareManager.calculateReminder(CareType.FERTILIZE, plant)
        }
    }

    LaunchedEffect(currentPlant) {
        if (currentPlant != null) {
            updateReminders()
        }
    }

    LaunchedEffect(triggerWaterReset) {
        if (triggerWaterReset > 0) {
            kotlinx.coroutines.delay(2000)
            waterCompleted = false
            updateReminders()
        }
    }

    LaunchedEffect(triggerLoosenReset) {
        if (triggerLoosenReset > 0) {
            kotlinx.coroutines.delay(2000)
            loosenCompleted = false
            updateReminders()
        }
    }

    LaunchedEffect(triggerFertilizeReset) {
        if (triggerFertilizeReset > 0) {
            kotlinx.coroutines.delay(2000)
            fertilizeCompleted = false
            updateReminders()
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFCFBF0)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DigitalClock()

            DateDisplay()

            if (loading) {
                CircularProgressIndicator(
                    color = Color(0xFF00CD00),
                    modifier = Modifier.padding(16.dp)
                )
            } else if (error != null) {
                val errorMessage = error
                Text(
                    text = errorMessage ?: "获取天气失败",
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(8.dp)
                )
            } else {
                WeatherInfo(weather)
            }

            CurrentPlant(selectedPlantName)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                if (PlantCareManager.isCareEnabled(CareType.WATER)) {
                    FunctionButton(
                        type = FunctionType.WATER,
                        reminder = waterReminder,
                        isJustCompleted = waterCompleted,
                        onTap = {
                            PlantCareManager.setLastCareTime(CareType.WATER)
                            waterCompleted = true
                            waterReminder = CareReminder("刚刚浇水", "已浇水", false)
                            triggerWaterReset++
                        }
                    )
                }

                if (PlantCareManager.isCareEnabled(CareType.LOOSEN)) {
                    FunctionButton(
                        type = FunctionType.LOOSEN,
                        reminder = loosenReminder,
                        isJustCompleted = loosenCompleted,
                        onTap = {
                            PlantCareManager.setLastCareTime(CareType.LOOSEN)
                            loosenCompleted = true
                            loosenReminder = CareReminder("刚刚松土", "已松土", false)
                            triggerLoosenReset++
                        }
                    )
                }

                if (PlantCareManager.isCareEnabled(CareType.FERTILIZE)) {
                    FunctionButton(
                        type = FunctionType.FERTILIZE,
                        reminder = fertilizeReminder,
                        isJustCompleted = fertilizeCompleted,
                        onTap = {
                            PlantCareManager.setLastCareTime(CareType.FERTILIZE)
                            fertilizeCompleted = true
                            fertilizeReminder = CareReminder("刚刚施肥", "已施肥", false)
                            triggerFertilizeReset++
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun WeatherInfo(weather: com.example.smartfarmbuddy.data.model.Weather?) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (weather != null) {
            Text(
                text = "${weather.weatherText} ${weather.temperature}°C/${weather.feelsLike}°C",
                fontSize = 16.sp,
                color = Color(0xFF666666),
                modifier = Modifier.padding(top = 8.dp)
            )

            Text(
                text = "${weather.windDir} 风/${weather.windScale}级",
                fontSize = 14.sp,
                color = Color(0xFF888888)
            )

            Text(
                text = "无持续风向小于${weather.windScale}级",
                fontSize = 14.sp,
                color = Color(0xFF888888)
            )

            Text(
                text = "湿度 ${weather.humidity}%",
                fontSize = 14.sp,
                color = Color(0xFF888888)
            )
        } else {
            Text(
                text = "多云 13°C/25°C",
                fontSize = 16.sp,
                color = Color(0xFF666666),
                modifier = Modifier.padding(top = 8.dp)
            )

            Text(
                text = "多云 风向/小于3级",
                fontSize = 14.sp,
                color = Color(0xFF888888)
            )

            Text(
                text = "无持续风向小于3级",
                fontSize = 14.sp,
                color = Color(0xFF888888)
            )
        }
    }
}

@Composable
fun CurrentPlant(plantName: String) {
    Text(
        text = plantName,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF00CD00),
        modifier = Modifier.padding(top = 12.dp)
    )
}

package com.example.smartfarmbuddy.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlinx.coroutines.delay

@Composable
fun DigitalClock() {
    var currentTime by remember { mutableStateOf(getCurrentTime()) }
    
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            currentTime = getCurrentTime()
        }
    }
    
    Text(
        text = currentTime,
        fontSize = 48.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF00CD00),
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

private fun getCurrentTime(): String {
    val calendar = Calendar.getInstance()
    val format = SimpleDateFormat("h:mm a", Locale.US)
    return format.format(calendar.time).uppercase()
}

@Composable
fun DateDisplay() {
    val calendar = Calendar.getInstance()
    val month = calendar.get(Calendar.MONTH) + 1
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val weekday = getWeekday(calendar.get(Calendar.DAY_OF_WEEK))
    
    Text(
        text = "${month}月${day}日 $weekday",
        fontSize = 18.sp,
        color = Color(0xFF666666),
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

private fun getWeekday(dayOfWeek: Int): String {
    return when (dayOfWeek) {
        Calendar.SUNDAY -> "星期日"
        Calendar.MONDAY -> "星期一"
        Calendar.TUESDAY -> "星期二"
        Calendar.WEDNESDAY -> "星期三"
        Calendar.THURSDAY -> "星期四"
        Calendar.FRIDAY -> "星期五"
        Calendar.SATURDAY -> "星期六"
        else -> ""
    }
}
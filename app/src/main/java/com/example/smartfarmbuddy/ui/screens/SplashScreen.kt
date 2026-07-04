package com.example.smartfarmbuddy.ui.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartfarmbuddy.MainActivity
import com.example.smartfarmbuddy.R
import kotlinx.coroutines.delay

private const val SPLASH_DISPLAY_LENGTH = 3000L // 延迟3秒

// 淡绿色背景
private val LightGreenBackground = Color(0xFFE8F5E9)

@Composable
fun SplashScreen() {
    val context = LocalContext.current
    
    LaunchedEffect(Unit) {
        delay(SPLASH_DISPLAY_LENGTH)
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
        (context as android.app.Activity).finish()
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGreenBackground),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 应用图标
        Image(
            painter = painterResource(id = R.mipmap.app_icon),
            contentDescription = "App Logo",
            modifier = Modifier.size(150.dp)
        )
        
        Text(
            text = "智能农苑助手",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2E7D32), // 深绿色文字
            modifier = Modifier.padding(top = 24.dp)
        )
        
        Text(
            text = "Smart Farm Buddy",
            fontSize = 16.sp,
            color = Color(0xFF558B2F), // 中绿色文字
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
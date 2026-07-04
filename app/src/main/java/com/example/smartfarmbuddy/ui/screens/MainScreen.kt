package com.example.smartfarmbuddy.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartfarmbuddy.R
import com.example.smartfarmbuddy.data.Plant
import androidx.compose.runtime.mutableStateOf

private val TopAppBarColor = Color(0xFF00CD00)
private val TabBackgroundColor = Color.White
private val SelectedTabColor = Color(0xFF00CD00)
private val UnselectedTabColor = Color(0xFF666666)

@Composable
fun MainScreen() {
    var selectedTab by remember { mutableIntStateOf(1) }
    var selectedPlant by remember { mutableStateOf<Plant?>(null) }
    val tabs = listOf("手册", "主页", "设置")

    if (selectedPlant != null) {
        PlantDetailScreen(plant = selectedPlant!!)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    selectedPlant = null
                }
                .padding(top = 56.dp, start = 16.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                text = "← 返回",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Green
            )
        }
        return
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            color = TopAppBarColor
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.app_icon),
                    contentDescription = "应用图标",
                    modifier = Modifier.size(40.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "智能农苑助手",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            color = TabBackgroundColor
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                tabs.forEachIndexed { index, tabName ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .clickable {
                                selectedTab = index
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = tabName,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = if (selectedTab == index) {
                                    SelectedTabColor
                                } else {
                                    UnselectedTabColor
                                }
                            )

                            if (selectedTab == index) {
                                Box(
                                    modifier = Modifier
                                        .width(40.dp)
                                        .height(3.dp)
                                        .background(SelectedTabColor)
                                        .padding(top = 4.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .pointerInput(Unit) {
                    var totalDrag = 0f
                    detectHorizontalDragGestures(
                        onHorizontalDrag = { _, dragAmount ->
                            totalDrag += dragAmount
                        },
                        onDragEnd = {
                            if (totalDrag > 100f) {
                                selectedTab = if (selectedTab > 0) selectedTab - 1 else tabs.size - 1
                            } else if (totalDrag < -100f) {
                                selectedTab = (selectedTab + 1) % tabs.size
                            }
                            totalDrag = 0f
                        }
                    )
                }
        ) {
            when (selectedTab) {
                0 -> PlantSearchScreen(onPlantClick = { plant ->
                    selectedPlant = plant
                })
                1 -> HomeScreen()
                2 -> AlarmScreen()
            }
        }
    }
}
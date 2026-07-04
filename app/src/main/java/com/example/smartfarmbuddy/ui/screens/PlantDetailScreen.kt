package com.example.smartfarmbuddy.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartfarmbuddy.R
import com.example.smartfarmbuddy.data.Plant

@Composable
fun PlantDetailScreen(plant: Plant) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color(0xFFFFFBF0)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = plant.imageResId),
                contentDescription = plant.name,
                modifier = Modifier
                    .size(200.dp)
                    .padding(top = 24.dp)
            )
            
            Text(
                text = plant.name,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333),
                modifier = Modifier.padding(top = 20.dp)
            )
            
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 20.dp),
                color = Color(0xFFDDDDDD)
            ) {}
            
            Text(
                text = "植物介绍",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp)
            )
            
            Text(
                text = plant.description,
                fontSize = 16.sp,
                color = Color(0xFF555555),
                lineHeight = 26.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp)
            )
            
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                color = Color(0xFFEEEEEE)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "护理频率",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333),
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    
                    CareFrequencyRow("浇水", "${plant.waterFrequencyDays}天", R.drawable.watering)
                    CareFrequencyRow("松土", "${plant.loosenFrequencyDays}天", R.drawable.loosen_the_soil)
                    CareFrequencyRow("施肥", "${plant.fertilizeFrequencyDays}天", R.drawable.fertilization)
                }
            }
            
            Text(
                text = "养护要点",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, top = 20.dp)
            )
            
            Text(
                text = plant.careTips,
                fontSize = 16.sp,
                color = Color(0xFF555555),
                lineHeight = 26.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp)
            )
        }
    }
}

@Composable
fun CareFrequencyRow(title: String, frequency: String, iconResId: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = title,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = title,
            fontSize = 16.sp,
            color = Color(0xFF333333),
            modifier = Modifier
                .padding(start = 12.dp)
                .width(60.dp)
        )
        Text(
            text = frequency,
            fontSize = 16.sp,
            color = Color(0xFF00CD00),
            fontWeight = FontWeight.Medium
        )
    }
}
package com.example.smartfarmbuddy.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.smartfarmbuddy.data.Plant
import com.example.smartfarmbuddy.data.PlantDataSource
import com.example.smartfarmbuddy.ui.components.ExpandableList

@Composable
fun PlantSearchScreen(onPlantClick: (Plant) -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFFFBF0)
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            item {
                ExpandableList(
                    categories = PlantDataSource.categories,
                    onPlantClick = onPlantClick
                )
            }
        }
    }
}
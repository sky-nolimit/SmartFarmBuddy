package com.example.smartfarmbuddy.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartfarmbuddy.data.Plant
import com.example.smartfarmbuddy.data.PlantCategory

@Composable
fun ExpandableList(
    categories: List<PlantCategory>,
    onPlantClick: (Plant) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        categories.forEach { category ->
            ExpandableCategory(
                category = category,
                onPlantClick = onPlantClick
            )
        }
    }
}

@Composable
fun ExpandableCategory(
    category: PlantCategory,
    onPlantClick: (Plant) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column {
        // 分类标题（可点击展开/收起）
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isExpanded = !isExpanded },
            color = Color(0xFFE8E8E8)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription = if (isExpanded) "收起" else "展开",
                        modifier = Modifier.size(20.dp),
                        tint = Color(0xFF666666)
                    )

                    Text(
                        text = category.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }

        // 植物列表（展开时显示）
        if (isExpanded) {
            Column {
                category.plants.forEach { plant ->
                    PlantItem(
                        plant = plant,
                        onClick = { onPlantClick(plant) }
                    )
                }
            }
        }
    }
}

@Composable
fun PlantItem(
    plant: Plant,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        color = Color.White
    ) {
        Text(
            text = plant.name,
            fontSize = 16.sp,
            color = Color(0xFF333333),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 40.dp)
        )
    }
}
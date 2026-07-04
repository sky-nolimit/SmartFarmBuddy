package com.example.smartfarmbuddy.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.example.smartfarmbuddy.data.CareReminder

enum class FunctionType {
    WATER,
    LOOSEN,
    FERTILIZE
}

@Composable
fun FunctionButton(
    type: FunctionType,
    reminder: CareReminder,
    isJustCompleted: Boolean,
    onTap: () -> Unit
) {
    val (iconRes, label, color) = when (type) {
        FunctionType.WATER -> Triple(
            R.mipmap.watering,
            "浇水",
            Color(0xFF4A90D9)
        )
        FunctionType.LOOSEN -> Triple(
            R.mipmap.loosen_the_soil,
            "松土",
            Color(0xFF8B7355)
        )
        FunctionType.FERTILIZE -> Triple(
            R.mipmap.fertilization,
            "施肥",
            Color(0xFF8B4513)
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable { onTap() }
            .padding(horizontal = 8.dp)
    ) {
        Surface(
            modifier = Modifier
                .size(64.dp)
                .padding(8.dp),
            color = if (isJustCompleted) Color(0xFFE8E8E8) else Color.White,
            shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
        ) {
            androidx.compose.foundation.Image(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                modifier = Modifier
                    .size(40.dp)
                    .padding(4.dp)
            )
        }

        Text(
            text = if (isJustCompleted) "已$label" else label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = if (isJustCompleted) Color(0xFF00CD00) else Color(0xFF666666),
            modifier = Modifier.padding(top = 4.dp)
        )

        if (isJustCompleted) {
            Text(
                text = "已$label",
                fontSize = 12.sp,
                color = Color(0xFF00CD00),
                modifier = Modifier.padding(top = 2.dp)
            )
        } else {
            Text(
                text = reminder.timeDiffText,
                fontSize = 11.sp,
                color = Color(0xFF888888),
                modifier = Modifier.padding(top = 2.dp)
            )
            Text(
                text = reminder.suggestion,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = if (reminder.isRecommended) Color(0xFFFF4444) else Color(0xFF00CD00),
                modifier = Modifier.padding(top = 1.dp)
            )
        }
    }
}

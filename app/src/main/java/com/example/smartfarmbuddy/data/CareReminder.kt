package com.example.smartfarmbuddy.data

data class CareReminder(
    val timeDiffText: String,
    val suggestion: String,
    val isRecommended: Boolean
)

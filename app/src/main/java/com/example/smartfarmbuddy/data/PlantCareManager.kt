package com.example.smartfarmbuddy.data

import android.content.Context
import android.content.SharedPreferences
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object PlantCareManager {
    private const val PREFS_NAME = "PlantCarePrefs"
    private const val KEY_LAST_WATER = "last_water_time"
    private const val KEY_LAST_LOOSEN = "last_loosen_time"
    private const val KEY_LAST_FERTILIZE = "last_fertilize_time"
    private const val KEY_WATER_FREQUENCY = "water_frequency"
    private const val KEY_LOOSEN_FREQUENCY = "loosen_frequency"
    private const val KEY_FERTILIZE_FREQUENCY = "fertilize_frequency"
    private const val KEY_SELECTED_PLANT = "selected_plant"
    private const val KEY_WATER_ENABLED = "water_enabled"
    private const val KEY_LOOSEN_ENABLED = "loosen_enabled"
    private const val KEY_FERTILIZE_ENABLED = "fertilize_enabled"

    private lateinit var prefs: SharedPreferences

    fun initialize(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun getSelectedPlant(): String {
        return prefs.getString(KEY_SELECTED_PLANT, "仙人掌") ?: "仙人掌"
    }

    fun setSelectedPlant(plantName: String) {
        prefs.edit().putString(KEY_SELECTED_PLANT, plantName).apply()
    }

    fun getLastCareTime(careType: CareType): Long {
        val key = when (careType) {
            CareType.WATER -> KEY_LAST_WATER
            CareType.LOOSEN -> KEY_LAST_LOOSEN
            CareType.FERTILIZE -> KEY_LAST_FERTILIZE
        }
        return prefs.getLong(key, 0)
    }

    fun setLastCareTime(careType: CareType, time: Long = System.currentTimeMillis()) {
        val key = when (careType) {
            CareType.WATER -> KEY_LAST_WATER
            CareType.LOOSEN -> KEY_LAST_LOOSEN
            CareType.FERTILIZE -> KEY_LAST_FERTILIZE
        }
        prefs.edit().putLong(key, time).apply()
    }

    fun getFrequencyDays(careType: CareType, plant: Plant): Int {
        val key = when (careType) {
            CareType.WATER -> KEY_WATER_FREQUENCY
            CareType.LOOSEN -> KEY_LOOSEN_FREQUENCY
            CareType.FERTILIZE -> KEY_FERTILIZE_FREQUENCY
        }
        val customFreq = prefs.getInt(key, -1)
        return if (customFreq > 0) customFreq else getDefaultFrequency(careType, plant)
    }

    fun setCustomFrequency(careType: CareType, days: Int) {
        val key = when (careType) {
            CareType.WATER -> KEY_WATER_FREQUENCY
            CareType.LOOSEN -> KEY_LOOSEN_FREQUENCY
            CareType.FERTILIZE -> KEY_FERTILIZE_FREQUENCY
        }
        prefs.edit().putInt(key, days).apply()
    }

    fun clearCustomFrequency(careType: CareType) {
        val key = when (careType) {
            CareType.WATER -> KEY_WATER_FREQUENCY
            CareType.LOOSEN -> KEY_LOOSEN_FREQUENCY
            CareType.FERTILIZE -> KEY_FERTILIZE_FREQUENCY
        }
        prefs.edit().remove(key).apply()
    }

    fun isCareEnabled(careType: CareType): Boolean {
        val key = when (careType) {
            CareType.WATER -> KEY_WATER_ENABLED
            CareType.LOOSEN -> KEY_LOOSEN_ENABLED
            CareType.FERTILIZE -> KEY_FERTILIZE_ENABLED
        }
        return prefs.getBoolean(key, true)
    }

    fun setCareEnabled(careType: CareType, enabled: Boolean) {
        val key = when (careType) {
            CareType.WATER -> KEY_WATER_ENABLED
            CareType.LOOSEN -> KEY_LOOSEN_ENABLED
            CareType.FERTILIZE -> KEY_FERTILIZE_ENABLED
        }
        prefs.edit().putBoolean(key, enabled).apply()
    }

    private fun getDefaultFrequency(careType: CareType, plant: Plant): Int {
        return when (careType) {
            CareType.WATER -> plant.waterFrequencyDays
            CareType.LOOSEN -> plant.loosenFrequencyDays
            CareType.FERTILIZE -> plant.fertilizeFrequencyDays
        }
    }

    fun calculateReminder(careType: CareType, plant: Plant): CareReminder {
        val lastTime = getLastCareTime(careType)
        val frequencyDays = getFrequencyDays(careType, plant)
        
        if (lastTime == 0L) {
            return CareReminder(
                timeDiffText = "从未${getCareTypeName(careType)}",
                suggestion = "建议${getCareTypeName(careType)}",
                isRecommended = true
            )
        }

        val now = System.currentTimeMillis()
        val diffMillis = now - lastTime
        val diffDays = diffMillis / (1000.0 * 60 * 60 * 24)

        val timeDiffText = when {
            diffDays < 1 -> {
                val hours = diffMillis / (1000.0 * 60 * 60)
                if (hours < 1) {
                    val minutes = diffMillis / (1000.0 * 60)
                    String.format("%.0f分钟", minutes)
                } else {
                    String.format("%.1f小时", hours)
                }
            }
            diffDays < 2 -> "1天"
            else -> String.format("%.0f天", diffDays)
        }

        val isRecommended = diffDays >= frequencyDays
        val suggestion = if (isRecommended) "建议${getCareTypeName(careType)}" else "不建议${getCareTypeName(careType)}"

        return CareReminder(
            timeDiffText = "距离上次${getCareTypeName(careType)}${timeDiffText}",
            suggestion = suggestion,
            isRecommended = isRecommended
        )
    }

    private fun getCareTypeName(careType: CareType): String {
        return when (careType) {
            CareType.WATER -> "浇水"
            CareType.LOOSEN -> "松土"
            CareType.FERTILIZE -> "施肥"
        }
    }

    fun formatTime(time: Long): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        return sdf.format(Date(time))
    }
}

enum class CareType {
    WATER,
    LOOSEN,
    FERTILIZE
}

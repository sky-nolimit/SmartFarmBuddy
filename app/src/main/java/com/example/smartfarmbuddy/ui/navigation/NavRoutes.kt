package com.example.smartfarmbuddy.ui.navigation

sealed class NavRoute(val route: String, val title: String) {
    object PlantSearch : NavRoute("plant_search", "植物查询")
    object Home : NavRoute("home", "主页")
    object Alarm : NavRoute("alarm", "提醒设置")
}

val bottomNavItems = listOf(
    NavRoute.PlantSearch,
    NavRoute.Home,
    NavRoute.Alarm
)
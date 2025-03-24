package com.example.fetchhiring.navigation

import java.lang.IllegalArgumentException

enum class HireScreens {
    HomeScreen,
    HireGroupDetailsScreen;

    companion object {
        fun fromRoute(route: String?): HireScreens = when (route?.substringBefore("/")) {
            HomeScreen.name -> HomeScreen
            HireGroupDetailsScreen.name -> HireGroupDetailsScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not found")
        }
    }
}
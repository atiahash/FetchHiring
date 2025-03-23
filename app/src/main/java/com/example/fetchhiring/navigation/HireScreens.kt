package com.example.fetchhiring.navigation

import java.lang.IllegalArgumentException

enum class HireScreens {
    HomeScreen,
    HireGroupedListScreen;

    companion object {
        fun fromRoute(route: String?): HireScreens = when (route?.substringBefore("/")) {
            HomeScreen.name -> HomeScreen
            HireGroupedListScreen.name -> HireGroupedListScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not found")
        }
    }
}
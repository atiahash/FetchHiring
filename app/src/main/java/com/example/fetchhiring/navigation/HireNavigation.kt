package com.example.fetchhiring.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fetchhiring.screen.HiringListScreen
import com.example.fetchhiring.viewmodel.HireViewModel

@Composable
fun HireNavigation(viewModel: HireViewModel, modifier: Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HireScreens.HomeScreen.name
    ) {
        composable(HireScreens.HomeScreen.name) {
            HiringListScreen(navController = navController, viewModel = viewModel, modifier = modifier)
        }
    }
}
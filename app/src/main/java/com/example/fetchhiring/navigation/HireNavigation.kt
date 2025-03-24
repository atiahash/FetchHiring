package com.example.fetchhiring.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fetchhiring.screen.HireGroupListDetailsScreen
import com.example.fetchhiring.screen.HomeScreen
import com.example.fetchhiring.viewmodel.HireViewModel

@Composable
fun HireNavigation(viewModel: HireViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HireScreens.HomeScreen.name
    ) {
        composable(HireScreens.HomeScreen.name) {
            HomeScreen(navController = navController, viewModel = viewModel)
        }

        // ..../HireGroupedListScreen/listId=listId
        composable(HireScreens.HireGroupDetailsScreen.name+"/{listId}",
            arguments = listOf(navArgument(name="listId") { type = NavType.IntType })
        ) { backStackEntry ->
            HireGroupListDetailsScreen(viewModel, backStackEntry.arguments?.getInt("listId") ?: 0, navController)
        }

    }
}
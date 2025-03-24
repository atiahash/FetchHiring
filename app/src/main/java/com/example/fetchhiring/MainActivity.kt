package com.example.fetchhiring

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.fetchhiring.navigation.HireNavigation
import com.example.fetchhiring.screen.HomeScreen
import com.example.fetchhiring.ui.theme.FetchHiringTheme
import com.example.fetchhiring.viewmodel.HireViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val hiringViewModel by viewModels<HireViewModel>()
            FetchHiringTheme {
                HireNavigation(viewModel = hiringViewModel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FetchHiringTheme {
        HomeScreen(navController = rememberNavController(), viewModel = HireViewModel())
    }
}
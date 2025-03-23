package com.example.fetchhiring.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun HireGroupListDetailsScreen(
    navHostController: NavHostController,
    listId: Int?,
    modifier: Modifier
) {
    Text(
        text = "Group List $listId ",
        modifier = modifier.padding(16.dp))
}
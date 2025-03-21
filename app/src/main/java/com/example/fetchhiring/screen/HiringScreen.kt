package com.example.fetchhiring.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fetchhiring.HiringViewModel

@Composable
fun HiringListScreen(viewModel: HiringViewModel, modifier: Modifier = Modifier) {
    val name = viewModel.hiringListState.collectAsStateWithLifecycle()
    Text(
        text = "Hello ${name.value}!",
        modifier = modifier
    )
}
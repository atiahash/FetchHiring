package com.example.fetchhiring.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fetchhiring.HiringViewModel

@Composable
fun HiringListScreen(viewModel: HiringViewModel, modifier: Modifier = Modifier) {
    val list = viewModel.hiringListState.collectAsStateWithLifecycle()

    LazyColumn {
        items(list.value) { hire ->
            Text(
                text = "Group ${hire.listId}",
                modifier = Modifier.padding(16.dp),
            )
            HireItems(names = hire.names)
            Divider(color = Color.LightGray, thickness = 1.dp)
        }
    }
}

@Composable
fun HireItems(names: List<String?>) {
    LazyRow {
        items(names) { name ->
            Text(
                text = "$name",
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}


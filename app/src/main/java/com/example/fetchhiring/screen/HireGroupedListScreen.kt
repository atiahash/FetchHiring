package com.example.fetchhiring.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.fetchhiring.navigation.HireScreens
import com.example.fetchhiring.viewmodel.HireGroups
import com.example.fetchhiring.viewmodel.HireViewModel
import com.example.fetchhiring.viewmodel.UiState

@Composable
fun HireGroupListDetailsScreen(
    viewModel: HireViewModel,
    listId: Int?,
    modifier: Modifier,
    navHostController: NavHostController,
) {
    val result = viewModel.hiringListState.collectAsStateWithLifecycle()
    when (val hireList = result.value) {
        is UiState.Loading -> {
            LoadingIndicator(modifier)
        }
        is UiState.Success -> {
            val groupedList: List<String?> = hireList.data.find {
                        it.listId == listId
                    }?.names ?: emptyList()

            LazyColumn(modifier = modifier.padding(top = 16.dp, bottom = 16.dp)) {
                items(groupedList) {
                    HireListItem(it)
                    Divider(modifier = Modifier.padding(top = 8.dp, bottom = 4.dp), color = Color.LightGray)
                }
            }
        }
        is UiState.Error -> {
            GenericErrorMessage(modifier)
        }
    }
}
package com.example.fetchhiring.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.fetchhiring.R
import com.example.fetchhiring.viewmodel.HireViewModel
import com.example.fetchhiring.viewmodel.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HireGroupListDetailsScreen(
    viewModel: HireViewModel,
    listId: Int,
    navHostController: NavHostController,
) {
    val result = viewModel.hiringListState.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.group, listId)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                navigationIcon = {
                    IconButton(onClick = {
                        navHostController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back Button")
                    }
                }
            )
        },
    ) { innerPadding ->
        when (val hireList = result.value) {
            is UiState.Loading -> {
                LoadingIndicator(Modifier.padding(innerPadding))
            }
            is UiState.Success -> {
                val groupedList: List<String?> = hireList.data.find {
                    it.listId == listId
                }?.names ?: emptyList()

                LazyColumn(modifier = Modifier.padding(innerPadding)) {
                    items(groupedList) {
                        HireListItem(it, Color.White)
                        Divider(modifier = Modifier.padding(top = 8.dp, bottom = 4.dp), color = Color.LightGray)
                    }
                }
            }
            is UiState.Error -> {
                GenericErrorMessage(Modifier.padding(innerPadding))
            }
        }
    }
}
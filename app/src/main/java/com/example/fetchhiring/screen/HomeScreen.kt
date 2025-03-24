package com.example.fetchhiring.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.fetchhiring.R
import com.example.fetchhiring.navigation.HireScreens
import com.example.fetchhiring.ui.theme.Typography
import com.example.fetchhiring.viewmodel.HireGroups
import com.example.fetchhiring.viewmodel.HireViewModel
import com.example.fetchhiring.viewmodel.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: HireViewModel, modifier: Modifier = Modifier) {
    val result = viewModel.hiringListState.collectAsStateWithLifecycle()
    val localContext = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        localContext.getString(R.string.app_name),
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
    ) { innerPadding ->
        when (val hireList = result.value) {
            is UiState.Loading -> {
                LoadingIndicator(modifier)
            }
            is UiState.Success -> {
                HireList(modifier = Modifier.padding(innerPadding), list = hireList.data) { listId ->
                    navController.navigate(route = HireScreens.HireGroupDetailsScreen.name+"/$listId")
                }
            }
            is UiState.Error -> {
                GenericErrorMessage(modifier)
            }
        }

    }

}

@Composable
fun GenericErrorMessage(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.generic_error_message),
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun LoadingIndicator(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun HireList(
    modifier: Modifier,
    list: List<HireGroups>,
    onNavigateClicked: (Int) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(list) { hire ->
            Row {
                Text(
                    text = stringResource(R.string.group, hire.listId),
                    modifier = Modifier.padding(
                        top = 8.dp,
                        bottom = 8.dp,
                        end = 16.dp,
                        start = 16.dp
                    ),
                    style = Typography.titleMedium
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = {
                    onNavigateClicked(hire.listId)
                }) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowRight,
                        tint = Color.Black,
                        contentDescription = "Navigate into Group ${hire.listId} List"
                    )
                }
            }
            HireItems(names = hire.names)
            Divider(modifier = Modifier.padding(top = 8.dp, bottom = 4.dp), color = Color.LightGray)
        }
    }
}

@Composable
fun HireItems(names: List<String?>) {
    LazyRow {
        items(names) { name ->
            HireListItem(name, Color.LightGray)
        }
    }
}

@Composable
fun HireListItem(name: String?, itemColor: Color) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(itemColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$name",
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center)
    }
}



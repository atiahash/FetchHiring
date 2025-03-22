package com.example.fetchhiring.screen


import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fetchhiring.HireViewModel
import com.example.fetchhiring.R
import com.example.fetchhiring.ui.theme.Typography

@Composable
fun HiringListScreen(viewModel: HireViewModel, modifier: Modifier = Modifier) {
    val list = viewModel.hiringListState.collectAsStateWithLifecycle()
    LazyColumn(modifier = modifier) {
        items(list.value) { hire ->
            Row {
                Text(
                    text = stringResource(R.string.group, hire.listId),
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, end = 16.dp, start = 16.dp),
                    style = Typography.titleMedium
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, end = 16.dp, start = 16.dp),
                    tint = Color.Black,
                    contentDescription = "Navigate into Group ${hire.listId} List"
                )
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
            Box(modifier = Modifier
                .padding(4.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Gray)) {
                Text(
                    text = "$name",
                    modifier = Modifier.padding(16.dp))
            }
        }
    }
}



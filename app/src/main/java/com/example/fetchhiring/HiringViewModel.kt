package com.example.fetchhiring

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HiringViewModel: ViewModel() {
    private val _hiringListState = MutableStateFlow("Fetch Hiring Team")
    val hiringListState: StateFlow<String> = _hiringListState
}


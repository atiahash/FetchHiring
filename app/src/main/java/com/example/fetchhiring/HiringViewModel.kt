package com.example.fetchhiring

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchhiring.data.Hire
import com.example.fetchhiring.data.HiringRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HiringViewModel(private val hiringRepository: HiringRepository = HiringRepository): ViewModel() {
    private val _hiringListState = MutableStateFlow<List<Hire>>(emptyList())
    val hiringListState: StateFlow<List<Hire>> = _hiringListState

    init {
        fetchHiringData()
    }

    private fun fetchHiringData() {
        viewModelScope.launch {
            val hireList = hiringRepository.getHiringList()
            _hiringListState.value = hireList
        }
    }
}

